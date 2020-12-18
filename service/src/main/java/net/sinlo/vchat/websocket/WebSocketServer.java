package net.sinlo.vchat.websocket;

import net.sinlo.vchat.entity.GroupMember;
import net.sinlo.vchat.service.IGroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import net.sinlo.vchat.dto.Message;
import net.sinlo.vchat.dto.ResponseMessage;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/webSocket/{sid}")
@RestController
public class WebSocketServer {

    private static IGroupMemberService groupMemberService;

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static Map<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<String, WebSocketServer>();


    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //接收sid
    private String sid = "";
    //群聊
    private static Map<Integer, Set> rooms = new ConcurrentHashMap<Integer, Set>();


    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        this.session = session;

        webSocketMap.put(sid, this);     //加入set中
        addOnlineCount();           //在线数加1

        this.joinRooms(sid);
//       log.info("有新窗口开始监听:"+sid+",当前在线人数为" + getOnlineCount());
        this.sid = sid;
        try {
            System.err.println("连接成功！" + sid);

            sendMessage("连接成功");
        } catch (IOException e) {
//            log.error("websocket IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketMap.remove(this);
        this.leaveRooms(this.sid);
        subOnlineCount();
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        //群发消息
        for (WebSocketServer item : webSocketMap.values()) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
//        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {

            this.session.getBasicRemote().sendText(message);

    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message, @PathParam("sid") String sid) throws IOException {
        for (WebSocketServer item : webSocketMap.values()) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
                if (sid == null) {
                    item.sendMessage(message);
                } else if (item.sid.equals(sid)) {
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                continue;
            }
        }
    }

    /**
     * 发送到群聊
     */
    public static void sendRoom(String message, @PathParam("sid") String sid) throws IOException {
        Set room = rooms.get(sid);
        room.forEach(item -> {
            WebSocketServer ws = webSocketMap.get(item);
            try {
                ws.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void joinRooms(String sid) {
        ArrayList<GroupMember> list = this.groupMemberService.getGroupMemberList(Integer.parseInt(sid));
        System.out.println("加入房间" + sid + "---" + list);
        list.forEach(item -> {
            if (!rooms.containsKey(item.getGroup_id())) {
                rooms.put(item.getGroup_id(), new HashSet());
            }
            System.out.println(sid + "进入房间" + item.getGroup_id());
            rooms.get(item.getGroup_id()).add(sid);
        });
    }

    /**
     * 离线
     *
     * @param sid
     */
    private void leaveRooms(String sid) {
        ArrayList<GroupMember> list = this.groupMemberService.getGroupMemberList(Integer.parseInt(sid));
        list.forEach(item -> {
            this.leaveRoomById(sid, item.getGroup_id());
        });
    }

    /**
     * 离开单个房间
     *
     * @param sid
     */
    private void leaveRoomById(String sid, int Rooms) {
        if (!rooms.containsKey(Rooms)) {
            rooms.put(Rooms, new HashSet());
        }
        rooms.get(Rooms).remove(sid);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    @Autowired
    private void setGroupMemberService(IGroupMemberService groupMemberService) {
        WebSocketServer.groupMemberService = groupMemberService;
    }

}
