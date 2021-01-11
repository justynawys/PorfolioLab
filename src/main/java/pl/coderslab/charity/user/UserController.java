package pl.coderslab.charity.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(Model model){
        model.addAttribute("user", new User());
        return "register";
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(User user){
        userService.saveUser(user);
        return "redirect:/login";
    }
    @RequestMapping("/admin")
    public String admin(Model model,@AuthenticationPrincipal CurrentUser currentUser){
        model.addAttribute("user", currentUser.getUser().getName());
        return "admin";
    }

}
