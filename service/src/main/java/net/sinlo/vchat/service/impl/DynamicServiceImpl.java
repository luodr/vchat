package net.sinlo.vchat.service.impl;

import net.sinlo.vchat.dto.RequestChatMessage;
import net.sinlo.vchat.dto.dynamic.CommentDynamicDto;
import net.sinlo.vchat.dto.dynamic.DynamicDto;
import net.sinlo.vchat.entity.*;
import net.sinlo.vchat.mapper.CommentMapper;
import net.sinlo.vchat.mapper.DynamicMapper;
import net.sinlo.vchat.mapper.GoodMapper;
import net.sinlo.vchat.mapper.MessageMapper;
import net.sinlo.vchat.service.IDynamicService;
import net.sinlo.vchat.service.IFriendService;
import net.sinlo.vchat.service.IMessagetService;
import net.sinlo.vchat.util.MyBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ldr
 * @since 2020-11-14
 */
@Service
public class DynamicServiceImpl implements IDynamicService {
    @Autowired
    private DynamicMapper dynamicMapper;
    @Autowired
    private GoodMapper goodMapper;
    @Autowired
    private IFriendService friendService;
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Dynamic sendDynamic(User user, DynamicDto dto) {
        Dynamic dynamic = new Dynamic(dto.getType(), dto.getContext(), dto.getImages());
        dynamic.setSend_user_id(user.getId());
        dynamic.setCreatedAt(LocalDateTime.now());
        dynamic.setUpdateAt(LocalDateTime.now());

        dynamicMapper.sendDynamic(dynamic);
        return dynamic;
    }

    @Override
    public List<Dynamic> getDynamics(int userId) {
        List ids = friendService.getFriendIDs(userId);
        ids.add(userId);
        System.out.println(ids);
        return dynamicMapper.getDynamics(ids, userId);
    }

    public Good goodDynamics(User user, int dynamicID) {
        Good good = new Good();
        good.setDynamic_id(dynamicID);
        good.setUser_id(user.getId());
        goodMapper.goodDynamic(good);
        if(good.getId()>0)
        return good;
        return  null;
    }

    @Override
    public Comment commentDynamics(User user, CommentDynamicDto dto) {
        Comment comment=new Comment();
        comment.setContext(dto.getContext());
        comment.setDynamic_id(dto.getId());
        comment.setUser_id(user.getId());
        commentMapper.goodDynamic(comment);
        return comment;
    }
}
