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

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.Valid;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import mvc.spring.web.dto.EventDto;
import mvc.spring.web.services.event.EventService;

@Controller
@RequestMapping("/events")
@SessionAttributes("event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/{clubId}/new")
    public String saveEventForm(@PathVariable("clubId") Long clubId, Model model) {

        EventDto event = new EventDto();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", event);
        return "clubs/events/events-create";
    }

    @PostMapping("/{clubId}/new")
    public String saveEvent(@PathVariable("clubId") Long clubId, @Valid @ModelAttribute("event") EventDto event)
            throws NotFoundException {
        eventService.save(clubId, event);
        return "redirect:/clubs/" + clubId;
    }

    @GetMapping("/search")
    public String searchClub(@RequestParam(value = "query") String query, Model model) {
        List<EventDto> foundEvents = eventService.search(query);
        model.addAttribute("events", foundEvents);
        return "clubs/events/events-list";
    }

    @GetMapping("/all")
    public String eventList(Model model) {
        List<EventDto> events = eventService.findAll();
        model.addAttribute("events", events);
        return "clubs/events/events-list";
    }

    @GetMapping("/{eventId}")
    public String getMethodName(@PathVariable long eventId, Model model) throws NotFoundException {
        EventDto event = eventService.findById(eventId);
        model.addAttribute("event", event);
        return "clubs/events/events-detail";
    }

    @GetMapping("/{eventId}/delete")
    public String deleteEvent(@PathVariable long eventId) {
        eventService.deleteById(eventId);
        return "redirect:/events/all";
    }

    @GetMapping("/{eventId}/edit")
    public String editEventForm(@PathVariable("eventId") long eventId,
            @NotNull Model model) throws NotFoundException {
        EventDto event = eventService.findById(eventId);
        model.addAttribute("event", event);
        return "clubs/events/events-edit";
    }

    @PostMapping("/{eventId}/edit")
    public String updateEvent(@PathVariable("eventId") long eventId,
            @Valid @ModelAttribute("event") EventDto event, SessionStatus status,
            BindingResult result) throws NotFoundException {

        if (result.hasErrors()) {
            return "clubs/events/events-edit";
        }
        event.setId(eventId);
        eventService.update(event);
        status.setComplete();
        return "redirect:/events/all";
    }

}
