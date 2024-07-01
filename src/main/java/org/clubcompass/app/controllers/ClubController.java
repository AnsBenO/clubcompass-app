package org.clubcompass.app.controllers;

import java.util.List;

import org.clubcompass.app.dto.ClubDto;
import org.clubcompass.app.models.UserEntity;
import org.clubcompass.app.security.SecurityUtil;
import org.clubcompass.app.services.club.impl.ClubServiceImpl;
import org.clubcompass.app.services.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.jetbrains.annotations.NotNull;

import jakarta.validation.Valid;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/clubs")
@SessionAttributes("club")
@RequiredArgsConstructor
public class ClubController {

    private final ClubServiceImpl clubService;
    private final UserService userService;

    @GetMapping("/all")
    public String listClubs(@NotNull Model model) {
        List<ClubDto> clubs = clubService.findAll();
        UserEntity user = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("clubs", clubs);
        return "clubs/clubs-list";
    }

    @GetMapping("/{clubId}")
    public String clubDetail(@PathVariable("clubId") long clubId, Model model) throws NotFoundException {

        ClubDto club = clubService.findById(clubId);
        UserEntity user = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("club", club);

        return "clubs/clubs-detail";

    }

    @GetMapping("/search")
    public String searchClub(@RequestParam(value = "query") String query, Model model) {
        List<ClubDto> foundClubs = clubService.search(query);
        UserEntity user = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("clubs", foundClubs);
        return "clubs/clubs-list";
    }

    @GetMapping("/new")
    public String createClubForm(@NotNull Model model) {
        ClubDto club = new ClubDto();
        model.addAttribute("club", club);
        return "clubs/clubs-create";
    }

    @PostMapping("/new")
    public String saveClub(@Valid @ModelAttribute("club") ClubDto club,
            @NotNull Model model,
            RedirectAttributes redirectAttributes,
            BindingResult result) {

        if (result.hasErrors()) {
            return "clubs/clubs-create";
        }
        clubService.save(club);
        redirectAttributes.addFlashAttribute("success", "Club Created Successfully");

        return "redirect:/clubs/all";

    }

    @GetMapping("/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") long clubId,
            RedirectAttributes redirectAttributes,
            @NotNull Model model) {
        ClubDto club;
        try {
            club = clubService.findById(clubId);
            if (!club.getCreatedBy().getUsername().equals(SecurityUtil.getSessionUser())) {
                redirectAttributes.addFlashAttribute("error", "You don't have permission to update this club.");
                return "redirect:/clubs/all";
            }
            model.addAttribute("club", club);
            return "clubs/clubs-edit";
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("error", "Club Not Found.");
            return "redirect:/clubs/all";
        }
    }

    @PatchMapping("/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") long clubId,
            @Valid @ModelAttribute("club") ClubDto club,
            BindingResult result,
            SessionStatus status,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (result.hasErrors()) {
            return "clubs/clubs-edit";
        }
        if (!club.getCreatedBy().getUsername().equals(SecurityUtil.getSessionUser())) {
            redirectAttributes.addFlashAttribute("error", "You don't have permission to update this club.");
            return "redirect:/clubs/all";
        }
        club.setId(clubId);
        clubService.update(club);
        status.setComplete();
        redirectAttributes.addFlashAttribute("success", "Club Updated Successfully");
        return "redirect:/clubs/all";
    }

    @DeleteMapping("/{clubId}/delete")
    public String deleteClub(@PathVariable long clubId, RedirectAttributes redirectAttributes) {
        try {
            ClubDto club = clubService.findById(clubId);
            if (!club.getCreatedBy().getUsername().equals(SecurityUtil.getSessionUser())) {
                redirectAttributes.addFlashAttribute("error", "You don't have permission to delete this club.");
                return "redirect:/clubs/all";
            }
            clubService.deleteById(clubId);
            redirectAttributes.addFlashAttribute("success", "Club Deleted Successfully");
            return "redirect:/clubs/all";
        } catch (NotFoundException e) {

            redirectAttributes.addFlashAttribute("error", "Club Not Found.");
            return "redirect:/clubs/all";
        }
    }

}
