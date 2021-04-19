package net.sinlo.vchat.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sinlo.vchat.authorization.ParamUser;
import net.sinlo.vchat.authorization.PassToken;
import net.sinlo.vchat.authorization.UserLoginToken;
import net.sinlo.vchat.dto.user.*;
import net.sinlo.vchat.entity.User;
import net.sinlo.vchat.service.IUserService;
import net.sinlo.vchat.util.MyBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

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
    public Map register(@RequestBody @Validated UserRegisterDto dto) {
         return this.userService.register(dto);
    }
    @PostMapping("update/img")
    @UserLoginToken
    public boolean updateImg(@ParamUser  @ApiIgnore User user, @RequestBody @Validated UserUpdateDto dto) {
        System.out.println(dto.getImg()+"----"+user.getId());
        return this.userService.updateImg(user.getId(),dto.getImg());
    }
    @ApiOperation("获取验证码")
    @GetMapping("code")
    public boolean getCode(@Validated GetCodeDto dto) {
        return this.userService.sendCode(dto);
    }

    @ApiOperation("获取本人的信息")
    @UserLoginToken
    @GetMapping("myInfo")
    public User myFriends(@ParamUser User user) {
        user.setPassword(null);
        return user;
    }
    @GetMapping("search")
    public User search(@ParamUser User user,@RequestParam(value = "phone") String phone) {
        return userService.search(phone);
    }
    @PostMapping("update/name")
    @UserLoginToken
    public boolean updateName(@ParamUser  @ApiIgnore User user, @RequestBody @Validated UserUpdateNameDto dto) {

        return this.userService.updateName(user.getId(),dto.getName());
    }

    @ApiOperation("修改密码")
    @PostMapping("forget")
    public Map updatePassword(@RequestBody @Validated UserRegisterDto dto) {
        return this.userService.updatePassword(dto);
    }
    }

