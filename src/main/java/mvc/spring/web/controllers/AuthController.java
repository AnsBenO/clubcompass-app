package mvc.spring.web.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import mvc.spring.web.dto.RegistrationDto;
import mvc.spring.web.services.user.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        if (isUserAuthenticated()) {
            return "redirect:/clubs/all";
        }
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") RegistrationDto user, BindingResult result) {
        if (isUserAuthenticated()) {
            return "redirect:/clubs/all";
        }
        if (result.hasErrors()) {
            return "auth/register";
        }
        userService.save(user);
        return "redirect:/clubs/all";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        if (isUserAuthenticated()) {
            return "redirect:/clubs/all";
        }
        return "auth/login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "auth/login";
    }

    private boolean isUserAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken);
    }

}
