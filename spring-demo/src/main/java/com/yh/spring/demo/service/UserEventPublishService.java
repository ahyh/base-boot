package com.yh.spring.demo.service;

import com.yh.spring.demo.event.UserAddEvent;
import com.yh.spring.demo.event.UserUpdateEvent;

/**
 * UserEvent相关事件
 *
 * @author yanhuan
 */
public interface UserEventPublishService {

    /**
     * 发布用户添加事件
     *
     * @param userAddEvent 用户添加事件
     * @return 是否成功发布
     */
    boolean publishUserAddEvent(UserAddEvent userAddEvent);

    /**
     * 发布用户更新事件
     *
     * @param userUpdateEvent 用户更新事件
     * @return 是否成功发布
     */
    boolean publishUserUpdateEvent(UserUpdateEvent userUpdateEvent);
}
