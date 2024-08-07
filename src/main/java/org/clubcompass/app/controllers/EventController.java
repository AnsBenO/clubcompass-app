package org.clubcompass.app.controllers;

import java.util.List;

import org.clubcompass.app.dto.ClubDto;
import org.clubcompass.app.dto.EventDto;
import org.clubcompass.app.entities.UserEntity;
import org.clubcompass.app.security.SecurityUtil;
import org.clubcompass.app.services.club.ClubService;
import org.clubcompass.app.services.event.EventService;
import org.clubcompass.app.services.user.UserService;
import org.jetbrains.annotations.NotNull;
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

import jakarta.validation.Valid;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/events")
@SessionAttributes("event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final UserService userService;
    private final ClubService clubService;

    @GetMapping("{clubId}/new")
    public String saveEventForm(@PathVariable("clubId") Long clubId, Model model,
            RedirectAttributes redirectAttributes) {
        ClubDto club;
        try {
            club = clubService.findById(clubId);
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("error", "Club not found.");
            return "redirect:/events/all";
        }
        if (!club.getCreatedBy().getUsername().equals(SecurityUtil.getSessionUser())) {
            redirectAttributes.addFlashAttribute("error", "You don't have permission to add events to this club.");
            return "redirect:/events/all";
        }
        EventDto event = new EventDto();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", event);
        return "clubs/events/events-create";
    }

    @PostMapping("{clubId}/new")
    public String saveEvent(@PathVariable("clubId") Long clubId,
            @Valid @ModelAttribute("event") EventDto event,
            BindingResult result,
            RedirectAttributes redirectAttributes, Model model) {
        ClubDto club;
        try {
            club = clubService.findById(clubId);
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("error", "Club Not Found.");
            return "redirect:/clubs/all";
        }
        if (!club.getCreatedBy().getUsername().equals(SecurityUtil.getSessionUser())) {
            redirectAttributes.addFlashAttribute("error", "You don't have permission to add events to this club.");
            return "redirect:/events/all";
        }
        if (result.hasErrors()) {
            model.addAttribute("clubId", clubId);
            model.addAttribute("event", event);
            return "clubs/events/events-create";
        }
        try {
            eventService.save(clubId, event);
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("error", "Event not found.");
            return "redirect:/events/all";
        }
        redirectAttributes.addFlashAttribute("success", "Event Created Successfully");
        return "redirect:/clubs/" + clubId;
    }

    @GetMapping("/search")
    public String searchEvent(@RequestParam(value = "query") String query, Model model) {
        List<EventDto> foundEvents = eventService.search(query);
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userService.findByUsername(username);

        model.addAttribute("user", user);
        model.addAttribute("events", foundEvents);
        return "clubs/events/events-list";
    }

    @GetMapping("/all")
    public String eventList(Model model) {
        List<EventDto> events = eventService.findAll();
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userService.findByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("events", events);
        return "clubs/events/events-list";
    }

    @GetMapping("/{eventId}")
    public String getEventDetail(@PathVariable long eventId,
            Model model,
            RedirectAttributes redirectAttributes) {
        EventDto event;
        try {
            event = eventService.findById(eventId);
            String username = SecurityUtil.getSessionUser();
            UserEntity user = userService.findByUsername(username);
            model.addAttribute("user", user);
            model.addAttribute("event", event);
            return "clubs/events/events-detail";
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("error", "Event Not Found.");
            return "redirect:/events/all";
        }

    }

    @GetMapping("/{eventId}/delete")
    public String showDeleteEvent(@PathVariable long eventId,
            RedirectAttributes redirectAttributes, Model model) {
        EventDto event;
        try {
            event = eventService.findById(eventId);
            if (!event.getClub().getCreatedBy().getUsername().equals(SecurityUtil.getSessionUser())) {
                redirectAttributes.addFlashAttribute("error", "You don't have permission to delete this event.");
                return "redirect:/event/all";
            }
            model.addAttribute("event", event);
            return "modals/delete-event";

        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("error", "Event Not Found.");
            return "redirect:/events/all";
        }

    }

    @DeleteMapping("/{eventId}/delete")
    public String deleteEvent(@PathVariable long eventId,
            RedirectAttributes redirectAttributes) {
        EventDto event;
        try {
            event = eventService.findById(eventId);
            if (!event.getClub().getCreatedBy().getUsername().equals(SecurityUtil.getSessionUser())) {
                redirectAttributes.addFlashAttribute("error", "You don't have permission to delete this event.");
                return "redirect:/event/all";
            }
            eventService.deleteById(eventId);
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("error", "Event Not Found.");
            return "redirect:/events/all";
        }
        redirectAttributes.addFlashAttribute("success", "Event Deleted Successfully");
        return "redirect:/events/all";
    }

    @GetMapping("/{eventId}/edit")
    public String editEventForm(@PathVariable("eventId") long eventId,
            RedirectAttributes redirectAttributes,
            @NotNull Model model) {
        EventDto event;
        try {
            event = eventService.findById(eventId);
            if (!event.getClub().getCreatedBy().getUsername().equals(SecurityUtil.getSessionUser())) {
                redirectAttributes.addFlashAttribute("error", "You don't have permission to update this event.");
                return "redirect:/clubs/all";
            }
            model.addAttribute("event", event);
            return "clubs/events/events-edit";
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("error", "Event Not Found.");
            return "redirect:/events/all";
        }
    }

    @PatchMapping("/{eventId}/edit")
    public String updateEvent(@PathVariable("eventId") long eventId,
            @Valid @ModelAttribute("event") EventDto event,
            BindingResult result,
            SessionStatus status,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("event", event);
            return "clubs/events/events-edit";
        }
        event.setId(eventId);
        try {
            if (!event.getClub().getCreatedBy().getUsername().equals(SecurityUtil.getSessionUser())) {
                redirectAttributes.addFlashAttribute("error", "You don't have permission to update this event.");
                return "redirect:/clubs/all";
            }
            eventService.update(event);
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("error", "Event Not Found.");
            return "redirect:/events/all";
        }
        status.setComplete();
        redirectAttributes.addFlashAttribute("success", "Event Updated Successfully");
        return "redirect:/events/all";
    }
}
