package MyWedShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "registration";
    }
    @PostMapping("/register")
    public String createUser(@RequestParam ("email")String email,@RequestParam ("name") String name, @RequestParam ("password") String password){
        User user =new User(email,name,password);
        userService.createUser(user);
        return "redirect:/login";
    }
    @GetMapping("/hello")
    public String security(){
        return "hello";
    }
}
