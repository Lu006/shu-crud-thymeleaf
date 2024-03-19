package org.example.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        Page<User> users=userService.page(new Page<User>(1,5));
        System.out.println(users.getRecords());
        model.addAttribute("users", users.getRecords());
        return "index";
    }

    @GetMapping("/user/new")
    public String showUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add-form";
    }

    @PostMapping("/user")
    public String addUser(User user) {
        boolean save = userService.save(user);
        System.out.println(save?"插入成功":"插入失败");
        return "redirect:/users";
    }

    // 其他操作，如删除和更新学生信息的方法可以根据需要自行添加
}
