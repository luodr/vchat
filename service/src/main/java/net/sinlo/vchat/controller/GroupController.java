package net.sinlo.vchat.controller;


import net.sinlo.vchat.authorization.ParamUser;
import net.sinlo.vchat.authorization.UserLoginToken;
import net.sinlo.vchat.dto.group.CreateGroupDto;
import net.sinlo.vchat.entity.Group;
import net.sinlo.vchat.entity.User;
import net.sinlo.vchat.service.IGroupMemberService;
import net.sinlo.vchat.service.IGroupService;
import net.sinlo.vchat.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ldr
 * @since 2020-11-14
 */
@RestController
@RequestMapping("api/group")
@UserLoginToken
public class GroupController {
    @Autowired
    IGroupService groupService;
    @Autowired
    IUserService userService;
    @Autowired
    IGroupMemberService groupMemberService;
    @PostMapping("create")
    public Group createGroup(@ParamUser @ApiIgnore User user, @RequestBody ArrayList<CreateGroupDto> array){

    return groupService.createGroup(user,array);
    }
    @PostMapping(value = "join/{groupId}")
    public boolean joinGroup(@ParamUser @ApiIgnore User user,@PathVariable int groupId, @RequestBody  ArrayList<CreateGroupDto> array, HttpServletRequest servletRequest){
        groupService.joinGroup(groupId,array);
        return true;
    }
    @PostMapping("leave/{groupId}")
    public boolean leaveGroup(@PathVariable int groupId,@ParamUser @ApiIgnore User user){
        return groupMemberService.leaveGroup(groupId,user.getId());
    }

    @GetMapping("")
    public List<Group>  groups( @ParamUser @ApiIgnore User user){
        List<Group> list = groupService.findByUserID(user.getId());
        list.forEach((item)->{
            item.setUsers(userService.findByGroupId(item.getId()));
        });
        return list;
    }
}

