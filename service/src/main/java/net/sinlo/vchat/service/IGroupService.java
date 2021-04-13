package net.sinlo.vchat.service;

import net.sinlo.vchat.dto.group.CreateGroupDto;
import net.sinlo.vchat.entity.Group;
import net.sinlo.vchat.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ldr
 * @since 2020-11-14
 */
public interface IGroupService  {
    Group createGroup(User user, List<CreateGroupDto> array);
    public boolean joinGroup(int groupId,List  array);

    List<Group> findByUserID(int userID);
}
