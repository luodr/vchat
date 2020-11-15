package net.sinlo.vchat.service.impl;

import net.sinlo.vchat.entity.Chat;
import net.sinlo.vchat.mapper.ChatMapper;
import net.sinlo.vchat.service.IChatService;
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
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements IChatService {

}
