package mvc.spring.web.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mvc.spring.web.dto.ClubDto;
import mvc.spring.web.services.impl.ClubService;

@RestController
@RequestMapping("/clubsRest")
public class clubRestController {

    private ClubService clubService;

    public clubRestController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/all")
    public List<ClubDto> findAllClubs() {
        return clubService.findAll();
    }

}
