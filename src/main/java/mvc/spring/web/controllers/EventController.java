package mvc.spring.web.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import mvc.spring.web.dto.EventDto;
import mvc.spring.web.models.Event;
import mvc.spring.web.services.event.EventService;

@Controller
@RequestMapping("/events")
public class EventController {

    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{clubId}/new")
    public String saveEventForm(@PathVariable("clubId") Long clubId, Model model) {

        Event event = new Event();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", event);
        return "clubs/events/event-create";
    }

    @PostMapping("/{clubId}/new")
    public String saveEvent(@PathVariable("clubId") Long clubId, @Valid @ModelAttribute("event") EventDto event) {
        eventService.save(clubId, event);
        return "redirect:/clubs/" + clubId;
    }

    @GetMapping("/all")
    public String eventList(Model model) {
        List<EventDto> events = eventService.findAll();
        model.addAttribute("events", events);
        return "clubs/events/events-list";
    }

}
