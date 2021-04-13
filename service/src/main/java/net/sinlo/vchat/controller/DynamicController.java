package net.sinlo.vchat.controller;


import io.swagger.annotations.Api;
import net.sinlo.vchat.authorization.ParamUser;
import net.sinlo.vchat.authorization.UserLoginToken;
import net.sinlo.vchat.dto.dynamic.CommentDynamicDto;
import net.sinlo.vchat.dto.dynamic.DynamicDto;
import net.sinlo.vchat.entity.Comment;
import net.sinlo.vchat.entity.Dynamic;
import net.sinlo.vchat.entity.Good;
import net.sinlo.vchat.entity.User;
import net.sinlo.vchat.service.IDynamicService;
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
@RequestMapping("api/dynamic")
@Api("动态控制器")
@UserLoginToken
public class DynamicController {
    @Autowired
    IDynamicService service;

    @GetMapping("/list")
    public List getDynamic(@ParamUser  @ApiIgnore User user) {
        return this.service.getDynamics(user.getId());
    }

    @PostMapping ("/send")
    public Dynamic sendDynamic(@ParamUser  @ApiIgnore User user,@RequestBody DynamicDto dto) {
        return service.sendDynamic(user,dto);
    }
    @PostMapping ("/good/{id}")
    public Good goodDynamic(@ParamUser  @ApiIgnore User user, @PathVariable()  int  id) {
        return service.goodDynamics(user,id);
    }
    @PostMapping ("/comment")
    public Comment commentDynamic(@ParamUser  @ApiIgnore User user, @RequestBody CommentDynamicDto dto) {
        return service.commentDynamics(user,dto);
    }

}

