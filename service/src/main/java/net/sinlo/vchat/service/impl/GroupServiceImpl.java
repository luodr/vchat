package net.sinlo.vchat.service.impl;

import net.sinlo.vchat.entity.Group;
import net.sinlo.vchat.mapper.GroupMapper;
import net.sinlo.vchat.service.IGroupService;
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
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements IGroupService {

}
