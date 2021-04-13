package net.sinlo.vchat.controller;


import io.swagger.annotations.Api;
import net.sinlo.vchat.authorization.ParamUser;
import net.sinlo.vchat.authorization.UserLoginToken;
import net.sinlo.vchat.dto.MessageReadDto;
import net.sinlo.vchat.dto.message.ImageToTextDto;
import net.sinlo.vchat.dto.message.SpeechDto;
import net.sinlo.vchat.dto.message.TranslateDto;
import net.sinlo.vchat.entity.Message;
import net.sinlo.vchat.entity.User;
import net.sinlo.vchat.service.IGroupChatService;
import net.sinlo.vchat.service.IMessagetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
    @Autowired
    IGroupChatService groupChatService;
    @GetMapping("/list")
    public List getMeesages(@ParamUser @ApiIgnore User user) {
        return this.service.geMeesages(user.getId());
    }

    @PostMapping("read")
    public boolean readMessage(@ParamUser @ApiIgnore User user, @RequestBody MessageReadDto dto) {
        System.out.println(dto);
        switch (dto.getType()) {
            case "GroupChat":
                return this.groupChatService.readGroupChat(user.getId(),dto.getChatID());
            case "PrivateChat":
                return this.service.readMessage(user.getId(), dto.getChatID());
            default:
                return false;
        }


    }
    @PostMapping("withdraw/{id}")
    public boolean withdrawMessage(@ParamUser @ApiIgnore User user, @PathVariable int  id) {

     return  service.withdrawMessage(user.getId(),id);

    }



    @PostMapping("speech")
    public String speech(@RequestBody SpeechDto dto) {
        System.out.println(dto.getPath());
//         service.speech(dto.getPath()).toString(2);
        // 语音识别
        return service.speechTX(dto.getPath());
    }

    @PostMapping("imageToText")
    public String imageToText(@RequestBody ImageToTextDto dto) {
        //图片转文字
        return service.imgToText(dto.getPath());
    }

    @PostMapping("translate")
    public String translated(@RequestBody TranslateDto dto) {
        // 翻译
        return service.translated(dto.getText(), dto.getSource(), dto.getTarget());
    }
}

