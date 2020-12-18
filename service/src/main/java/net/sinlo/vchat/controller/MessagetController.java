package net.sinlo.vchat.controller;


import io.swagger.annotations.Api;
import net.sinlo.vchat.authorization.ParamUser;
import net.sinlo.vchat.authorization.UserLoginToken;
import net.sinlo.vchat.entity.User;
import net.sinlo.vchat.service.IMessagetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ldr
 * @since 2020-11-14
 */
@RestController
@RequestMapping("api/message")
@Api("聊天信息控制器")
@UserLoginToken
public class MessagetController {
    @Autowired
    IMessagetService service;

    @GetMapping("/list")
    public List getMeesages(@ParamUser User user) {

        return this.service.geMeesages(user.getId());
    }

    @PutMapping("{sendUserID}")
    public boolean readMessage(@ParamUser User user,@PathVariable() int sendUserID) {
        System.out.println(sendUserID+"sendUserID"+this.service.readMessage(user.getId(), sendUserID));
        return this.service.readMessage(user.getId(), sendUserID);

    }

}

