package org.clubcompass.app.controllers;

import org.clubcompass.app.security.SecurityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
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
