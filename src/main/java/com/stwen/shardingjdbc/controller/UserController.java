package com.stwen.shardingjdbc.controller;


import com.stwen.shardingjdbc.entity.User;
import com.stwen.shardingjdbc.service.IUserService;
import io.shardingsphere.api.HintManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author stwen_gan
 * @since 2020-09-12
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @PutMapping("/save")
    public Object save() {
        User user = new User();
        user.setName("新增");
        return userService.save(user);
    }

    @GetMapping("/list")
    public Object list() {
        // 强制路由主库
        HintManager.getInstance().setMasterRouteOnly();
        return userService.list();
    }

}
