package net.sinlo.vchat.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sinlo.vchat.authorization.PassToken;
import net.sinlo.vchat.dto.user.LoginDto;
import net.sinlo.vchat.dto.user.UserRegisterDto;
import net.sinlo.vchat.entity.User;
import net.sinlo.vchat.service.IUserService;
import net.sinlo.vchat.util.MyBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ldr
 * @since 2020-11-14
 */
@RestController("/vchat/user")
@Api("用户")
public class UserController {
    @Autowired
    IUserService userService;

    @ApiOperation("登录")
    @GetMapping("getUser")
    @PassToken
    public String login(LoginDto dto) {
        User user = MyBeanUtils.copyProperties(dto, User.class);
        return this.userService.login(user);
    }

    @ApiOperation("注册")
    @PostMapping("register")
    public boolean register(@Validated UserRegisterDto dto) {
        User user = MyBeanUtils.copyProperties(dto, User.class);
        return this.userService.register(user);
    }
}

