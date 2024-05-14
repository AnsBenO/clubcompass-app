package mvc.spring.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import mvc.spring.web.security.SecurityUtil;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping
public class HomeController {

    @GetMapping("/")
    public String getHome(Model model) {
        String username = SecurityUtil.getSessionUser();
        model.addAttribute("username", username);
        return "home";
    }

    @GetMapping("/home")
    public RedirectView redirectToHome() {
        return new RedirectView("/", true);
    }

}
