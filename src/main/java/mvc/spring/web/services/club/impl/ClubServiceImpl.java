package mvc.spring.web.services.club.impl;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import mvc.spring.web.dto.ClubDto;
import mvc.spring.web.models.Club;
import mvc.spring.web.models.UserEntity;
import mvc.spring.web.repositories.ClubRepository;
import mvc.spring.web.repositories.UserRepository;
import mvc.spring.web.security.SecurityUtil;
import mvc.spring.web.services.club.ClubService;
import mvc.spring.web.mappers.ClubMapper;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final UserRepository userRepository;

    @Override
    public List<ClubDto> findAll() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream()
                .map(ClubMapper::mapToClubDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClubDto save(ClubDto clubDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        clubDto.setCreatedBy(user);
        Club club = ClubMapper.mapToClubEntity(clubDto);
        Club addedClub = clubRepository.save(club);
        return ClubMapper.mapToClubDto(addedClub);
    }

    @Override
    public ClubDto findById(long id) throws NotFoundException {
        Optional<Club> foundClub = clubRepository.findById(id);
        if (foundClub.isPresent()) {
            Club club = foundClub.get();
            return ClubMapper.mapToClubDto(club);
        }
        throw new NotFoundException("Club not found");
    }

    @Override
    public void update(ClubDto clubDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        clubDto.setCreatedBy(user);
        Club club = ClubMapper.mapToClubEntity(clubDto);

        clubRepository.save(club);

    }

    @Override
    public void deleteById(Long id) {
        Optional<Club> clubOptional = clubRepository.findById(id);
        if (clubOptional.isPresent()) {
            clubRepository.deleteById(id);
        }

    }

    @Override
    public List<ClubDto> search(String query) {
        List<Club> foundClubs = clubRepository.searchClub(query);
        return foundClubs.stream()
                .map(ClubMapper::mapToClubDto)
                .collect(Collectors.toList());
    }

}
