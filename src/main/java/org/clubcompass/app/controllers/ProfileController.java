package org.clubcompass.app.controllers;

import org.clubcompass.app.dto.ProfileDto;
import org.clubcompass.app.security.SecurityUtil;
import org.clubcompass.app.services.profile.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/profile")
    public String getProfileByUsername(RedirectAttributes redirectAttributes, Model model) {
        String username = SecurityUtil.getSessionUser();
        try {
            ProfileDto profile = profileService.findProfileByUsername(username);
            model.addAttribute("profile", profile);
            return "user/profile";
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("error", "Profile Not Found.");
            return "redirect:/";
        }
    }

}
