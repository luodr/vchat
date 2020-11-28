package net.sinlo.vchat.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sinlo.vchat.authorization.PassToken;
import net.sinlo.vchat.dto.user.GetCodeDto;
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
@RestController
@Api("用户")
@RequestMapping("api")
public class UserController {
    @Autowired
    IUserService userService;

    @ApiOperation("登录")
    @PostMapping("login")
    @PassToken
    public Object login(@RequestBody @Validated LoginDto dto) {
        return this.userService.login(dto);
    }
    @ApiOperation("注册")
    @PostMapping("register")
    public boolean register(@RequestBody @Validated UserRegisterDto dto) {
         return this.userService.register(dto);
    }

    @ApiOperation("获取验证码")
    @GetMapping("code")
    public boolean getCode(@Validated GetCodeDto dto) {
        return this.userService.sendCode(dto);
    }


    }

