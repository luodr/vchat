package net.sinlo.vchat.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sinlo.vchat.dto.ResponseChatMessage;
import net.sinlo.vchat.dto.WebSocketMessageDto;
import net.sinlo.vchat.entity.*;
import net.sinlo.vchat.service.IGroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import net.sinlo.vchat.dto.RequestChatMessage;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import net.sinlo.vchat.util.TokenUtil;

@ServerEndpoint("/webSocket/{token}")
@RestController
public class WebSocketServer {

    private static IGroupMemberService groupMemberService;

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static Map<Integer, WebSocketServer> webSocketMap = new ConcurrentHashMap<Integer, WebSocketServer>();

    static ObjectMapper objectMapper;
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //接收token
    private String token = "";
    private int id;
    private User user;
    //群聊
    private static Map<Integer, Set> rooms = new ConcurrentHashMap<Integer, Set>();
    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) {

        this. user = TokenUtil.getUser(token);
        if (user != null) {
            this.session = session;
            this.id = user.getId();
            webSocketMap.put(user.getId(), this);     //加入set中
            addOnlineCount();           //在线数加1
            this.joinRooms(id);
            this.token = token;
        }

    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        System.out.println("离开房间" + this.id);
        webSocketMap.remove(this.id);
        this.leaveRooms(this.id);
        subOnlineCount();
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        if(message.equals("HeartBeat")){
            System.out.println("心跳：" + message);
            try {
                this.sendMessage("HeartBeat");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
//            System.out.println("接收到的：" + message);
//            try {
//                RequestChatMessage chatMessage =  objectMapper.readValue(message, RequestChatMessage.class);
//                WebSocketServer.sendTo(chatMessage,this.user);
//
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }

        }
    }
//    public static void sendTo(RequestChatMessage chatMessage,User user){
//      switch (chatMessage.getContentType()){
//          case "PrivateChat":
//              sendPrivate(chatMessage,  user);
//              break;
//          case "GroupChat":
//              break;
//      }
//    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        // log.error("发生错误");
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
    public  void sendInfo(String message, @PathParam("token") String token) throws IOException {
        for (WebSocketServer item : webSocketMap.values()) {
            try {
                //这里可以设定只推送给这个token的，为null则全部推送
                if (token == null) {
                    item.sendMessage(message);
                } else if (item.token.equals(token)) {
                    item.sendMessage(message);
                }
            } catch (IOException  e) {
                continue;
            }
        }
    }

    /**
     * 发送到群聊
     */
    public static void sendRoom(GroupChat message, int MyUserID)  {
        Set room = rooms.get(message.getTo_group_id());
        if(room==null){return;}
        room.forEach(item -> {
//            if(MyUserID==((Integer)item)){return;}
            WebSocketServer ws = webSocketMap.get(item);
            if(MyUserID==((Integer)item)||ws==null){return;}
            try {
                WebSocketMessageDto<Message> data=new WebSocketMessageDto("GroupChat",message);
                ws.sendMessage(objectMapper.writeValueAsString(data));
            } catch (IOException  e) {
                System.out.println("发送群聊信息失败！");
                e.printStackTrace();
            }
        });
    }

    /**
     *  私聊发送
     * @param message
     * @throws IOException
     */
    public static  void sendPrivate(Message message){
       WebSocketServer socketServer=webSocketMap.get(message.getTo_user_id());
       if(socketServer!=null){
           try {
               message.setSelf(false);
               WebSocketMessageDto<Message> data=new WebSocketMessageDto("PrivateChat",message);
               socketServer.sendMessage(objectMapper.writeValueAsString(data));
           } catch (IOException e) {
               e.printStackTrace();
           }
       }

    }

    /**
     *  好友申请
     * @param friendAdd
     * @throws IOException
     */
    public static  void addFriend(FriendAdd friendAdd){
        WebSocketServer socketServer=webSocketMap.get(friendAdd.getTo_user_id());
        if(socketServer!=null){
            try {
                WebSocketMessageDto<FriendAdd> data=new WebSocketMessageDto("addFriend",friendAdd);
                socketServer.sendMessage(objectMapper.writeValueAsString(data));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    private void joinRooms(int id) {
        List<GroupMember> list = this.groupMemberService.getGroupMemberList(id);
        System.out.println("加入房间" + id + "---" + list);
        list.forEach(item -> {
            if (!rooms.containsKey(item.getGroup_id())) {
                rooms.put(item.getGroup_id(), new HashSet());
            }
            System.out.println(id + "进入房间" + item.getGroup_id());
            rooms.get(item.getGroup_id()).add(id);
        });
    }

    /**
     * 离线
     *
     * @param id
     */
    private void leaveRooms(int id) {
        List<GroupMember> list = this.groupMemberService.getGroupMemberList(id);
        list.forEach(item -> {
            this.leaveRoomById(id, item.getGroup_id());
        });
    }

    /**
     * 离开单个房间
     *
     * @param id
     */
    public void leaveRoomById(int id, int Rooms) {
        if (!rooms.containsKey(Rooms)) {
            rooms.put(Rooms, new HashSet());
        }
        rooms.get(Rooms).remove(id);
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

    @Autowired
    private void setObjectMapper(ObjectMapper objectMapper) {
        WebSocketServer.objectMapper = objectMapper;
    }
}
