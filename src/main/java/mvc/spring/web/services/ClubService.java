package mvc.spring.web.services;

import java.util.List;

import javassist.NotFoundException;
import mvc.spring.web.dto.ClubDto;

public interface ClubService {

    List<ClubDto> findAllClubs();

    ClubDto saveClub(ClubDto club);

    ClubDto findClubById(long id) throws NotFoundException;

    void updateClub(ClubDto club);

    void deleteClubById(long id);

}
