package net.sinlo.vchat.service;

import net.sinlo.vchat.dto.dynamic.CommentDynamicDto;
import net.sinlo.vchat.dto.dynamic.DynamicDto;
import net.sinlo.vchat.entity.Comment;
import net.sinlo.vchat.entity.Dynamic;
import net.sinlo.vchat.entity.Good;
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

public interface IDynamicService {
    Dynamic  sendDynamic(User user, DynamicDto dto);
    List<Dynamic>  getDynamics(int userId);
     Good goodDynamics(User user,int dynamicID);
    Comment commentDynamics(User user, CommentDynamicDto dto);
}
