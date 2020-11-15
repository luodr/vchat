package net.sinlo.vchat.service.impl;

import net.sinlo.vchat.entity.Groupmember;
import net.sinlo.vchat.mapper.GroupmemberMapper;
import net.sinlo.vchat.service.IGroupmemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ldr
 * @since 2020-11-14
 */
@Service
public class GroupmemberServiceImpl extends ServiceImpl<GroupmemberMapper, Groupmember> implements IGroupmemberService {

}
