package mvc.spring.web.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.Valid;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import mvc.spring.web.dto.ClubDto;
import mvc.spring.web.dto.EventDto;
import mvc.spring.web.models.UserEntity;
import mvc.spring.web.security.SecurityUtil;
import mvc.spring.web.services.club.ClubService;
import mvc.spring.web.services.event.EventService;
import mvc.spring.web.services.user.UserService;

@Controller
@RequestMapping("/events")
@SessionAttributes("event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final UserService userService;
    private final ClubService clubService;

    @GetMapping("/{clubId}/new")
    public String saveEventForm(@PathVariable("clubId") Long clubId, Model model,
            RedirectAttributes redirectAttributes) {
        ClubDto club;
        try {
            club = clubService.findById(clubId);
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("error", "Club not found.");
            return "redirect:/event/all";
        }
        if (!club.getCreatedBy().getUsername().equals(SecurityUtil.getSessionUser())) {
            redirectAttributes.addFlashAttribute("error", "You don't have permission to add events to this club.");
            return "redirect:/event/all";
        }
        EventDto event = new EventDto();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", event);
        return "clubs/events/events-create";
    }

    @PostMapping("/{clubId}/new")
    public String saveEvent(@PathVariable("clubId") Long clubId, @Valid @ModelAttribute("event") EventDto event,
            RedirectAttributes redirectAttributes) {
        ClubDto club;
        try {
            club = clubService.findById(clubId);
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("error", "Club not found.");
            return "redirect:/clubs/all";
        }
        if (!club.getCreatedBy().getUsername().equals(SecurityUtil.getSessionUser())) {
            redirectAttributes.addFlashAttribute("error", "You don't have permission to add events to this club.");
            return "redirect:/event/all";
        }
        try {
            eventService.save(clubId, event);
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("error", "Event Not Found.");
            return "redirect:/events/all";
        }
        return "redirect:/clubs/" + clubId;
    }

    @GetMapping("/search")
    public String searchEvent(@RequestParam(value = "query") String query, Model model) {
        List<EventDto> foundEvents = eventService.search(query);
        UserEntity user = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("events", foundEvents);
        return "clubs/events/events-list";
    }

    @GetMapping("/all")
    public String eventList(Model model) {
        List<EventDto> events = eventService.findAll();
        UserEntity user = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("events", events);
        return "clubs/events/events-list";
    }

    @GetMapping("/{eventId}")
    public String getEventDetail(@PathVariable long eventId, Model model, RedirectAttributes redirectAttributes) {
        EventDto event;
        try {
            event = eventService.findById(eventId);
            UserEntity user = new UserEntity();
            String username = SecurityUtil.getSessionUser();
            if (username != null) {
                user = userService.findByUsername(username);
                model.addAttribute("user", user);
            }
            model.addAttribute("user", user);
            model.addAttribute("event", event);
            return "clubs/events/events-detail";
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("error", "Event Not Found.");
            return "redirect:/events/all";
        }

    }

    @PostMapping("/{eventId}/delete")
    public String deleteEvent(@PathVariable long eventId, RedirectAttributes redirectAttributes) {
        try {
            EventDto event = eventService.findById(eventId);
            if (!event.getClub().getCreatedBy().equals(SecurityUtil.getSessionUser())) {
                redirectAttributes.addFlashAttribute("error", "You don't have permission to delete this event.");
                return "redirect:/event/all";
            }
            eventService.deleteById(eventId);
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("error", "Event Not Found.");
            return "redirect:/events/all";
        }
        return "redirect:/events/all";
    }

    @GetMapping("/{eventId}/edit")
    public String editEventForm(@PathVariable("eventId") long eventId, RedirectAttributes redirectAttributes,
            @NotNull Model model) {
        EventDto event;
        try {
            event = eventService.findById(eventId);
            if (!event.getClub().getCreatedBy().equals(SecurityUtil.getSessionUser())) {
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

    @PostMapping("/{eventId}/edit")
    public String updateEvent(@PathVariable("eventId") long eventId,
            @Valid @ModelAttribute("event") EventDto event, SessionStatus status, RedirectAttributes redirectAttributes,
            BindingResult result) {

        if (result.hasErrors()) {
            return "clubs/events/events-edit";
        }
        event.setId(eventId);
        try {
            if (!event.getClub().getCreatedBy().equals(SecurityUtil.getSessionUser())) {
                redirectAttributes.addFlashAttribute("error", "You don't have permission to update this event.");
                return "redirect:/clubs/all";
            }
            eventService.update(event);
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("error", "Event Not Found.");
            return "redirect:/events/all";
        }
        status.setComplete();
        return "redirect:/events/all";
    }

}
