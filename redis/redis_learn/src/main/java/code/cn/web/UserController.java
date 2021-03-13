package code.cn.web;

import code.cn.model.User;
import code.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private static final String SUCCESS = "success";

    private static final String FAILD = "faild";

    @GetMapping("/initUserData")
    public String initUserData() {
        boolean operateFlag = this.userService.initUserData();
        if (operateFlag) {
            return SUCCESS;
        } else {
            return FAILD;
        }
    }

    @GetMapping("/findUserById/{id}")
    public User findUserById(@PathVariable("id") Integer id) {
        return this.userService.findUserById(id);
    }
}
