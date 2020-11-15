package net.sinlo.vchat.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sinlo.vchat.dto.user.UserDto;
import net.sinlo.vchat.entity.User;
import net.sinlo.vchat.service.IUserService;
import net.sinlo.vchat.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
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
       public User login(int id){
           return  this.userService.getById(id);
       }
       @ApiOperation("注册")
       @PostMapping("register")
       public boolean register(@Validated UserDto dto){
            User user = MyBeanUtils.copyProperties(dto,User.class);
           System.out.println(user);
        return  this.userService.save(user);
       }
}

