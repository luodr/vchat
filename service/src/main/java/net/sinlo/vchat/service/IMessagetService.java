package net.sinlo.vchat.service;

import net.sinlo.vchat.entity.Message;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ldr
 * @since 2020-11-14
 */

public interface IMessagetService {
    List<Message> geMeesages(int userId);
    boolean readMessage(int userId,int send_userId);
}
