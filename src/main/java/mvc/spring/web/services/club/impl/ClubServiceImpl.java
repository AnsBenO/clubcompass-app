package mvc.spring.web.services.club.impl;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javassist.NotFoundException;
import mvc.spring.web.dto.ClubDto;
import mvc.spring.web.models.Club;
import mvc.spring.web.repositories.ClubRepository;
import mvc.spring.web.services.club.ClubService;
import mvc.spring.web.mappers.ClubMapper;;

@Service
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;

    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubDto> findAll() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream()
                .map((club) -> ClubMapper.mapToClubDto(club))
                .collect(Collectors.toList());
    }

    @Override
    public ClubDto save(ClubDto clubDto) {
        Club club = ClubMapper.mapToClubEntity(clubDto);
        Club addedClub = clubRepository.save(club);
        return ClubMapper.mapToClubDto(addedClub);
    }

    @Override
    public ClubDto findById(long id) throws NotFoundException {
        Optional<Club> foundClub = clubRepository.findById(id);
        return foundClub.map((club) -> ClubMapper.mapToClubDto(club))

                .orElseThrow(() -> new NotFoundException("Club not found with id: " + id));
    }

    @Override
    public void update(ClubDto clubDto) {
        Club club = ClubMapper.mapToClubEntity(clubDto);
        clubRepository.save(club);
    }

    @Override
    public void deleteById(Long id) {
        clubRepository.findById(id)
                .ifPresent(clubRepository::delete);
    }

    @Override
    public List<ClubDto> search(String query) {
        List<Club> foundClubs = clubRepository.searchClub(query);
        return foundClubs.stream()
                .map((club) -> ClubMapper.mapToClubDto(club))
                .collect(Collectors.toList());
    }

}
