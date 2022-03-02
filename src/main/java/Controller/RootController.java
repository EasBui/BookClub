package Controller;

import Entity.User.User;
import Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RequestMapping("/")
@Controller
public class RootController /*implements Controller*/ {
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String root() {
        return "welcome";
    }

    @RequestMapping(value = "/form/register", method= RequestMethod.GET)
    public  String registerForm() {
        return "register";
    }

    @RequestMapping(value = "/register", method= RequestMethod.POST)
    public  String register(
            @RequestParam String account,
            @RequestParam String password,
            @RequestParam String name,
            @RequestParam String profile) {
        User user = new User(
            -1,
            account,
            password,
            name,
            profile,
            Timestamp.valueOf(LocalDateTime.now()),
            "ROLE_USER",
            true
        );
        if(userService.registerUser(user)) {
            return "redirect:/form/login";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/form/login", method= RequestMethod.GET)
    public String loginForm() {
        return "login";
    }

}
