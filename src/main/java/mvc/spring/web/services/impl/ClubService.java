package mvc.spring.web.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javassist.NotFoundException;
import mvc.spring.web.dto.ClubDto;
import mvc.spring.web.models.Club;
import mvc.spring.web.repositories.ClubRepository;
import mvc.spring.web.services.CrudService;

@Service
public class ClubService implements CrudService<ClubDto, Long> {

    private ClubRepository clubRepository;

    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubDto> findAll() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map(this::mapToClubDto).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public ClubDto save(ClubDto clubDto) {
        Club club = mapToClubEntity(clubDto);
        Club addedClub = clubRepository.save(club);
        return mapToClubDto(addedClub);
    }

    @Override
    public ClubDto findById(long id) throws NotFoundException {
        Optional<Club> foundClub = clubRepository.findById(id);
        if (foundClub.isPresent()) {
            return mapToClubDto(foundClub.get());
        } else {
            throw new NotFoundException("Club not found with id: " + id);
        }
    }

    @Override
    public void update(ClubDto clubDto) {
        Club club = mapToClubEntity(clubDto);
        clubRepository.save(club);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Club> foundClubOptional = clubRepository.findById(id);
        if (foundClubOptional.isPresent()) {
            Club foundClub = foundClubOptional.get();
            clubRepository.delete(foundClub);
        }
    }

    private ClubDto mapToClubDto(@NotNull Club club) {
        return ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .description(club.getDescription())
                .createdAt(club.getCreatedAt())
                .updatedAt(club.getUpdatedAt())
                .build();
    }

    private Club mapToClubEntity(@NotNull ClubDto clubDto) {
        return Club.builder()
                .id(clubDto.getId())
                .title(clubDto.getTitle())
                .photoUrl(clubDto.getPhotoUrl())
                .description(clubDto.getDescription())
                .createdAt(clubDto.getCreatedAt())
                .updatedAt(clubDto.getUpdatedAt())
                .build();
    }

}
