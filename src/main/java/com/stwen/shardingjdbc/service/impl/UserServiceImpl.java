package com.stwen.shardingjdbc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stwen.shardingjdbc.dao.UserDao;
import com.stwen.shardingjdbc.entity.User;
import com.stwen.shardingjdbc.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stwen_gan
 * @since 2020-09-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {

    @Resource
    private UserDao userDao;

}
