package mvc.spring.web.services.club;

import java.util.List;

import javassist.NotFoundException;
import mvc.spring.web.dto.ClubDto;
import mvc.spring.web.services.common.Searchable;

public interface ClubService extends Searchable<ClubDto> {
    List<ClubDto> findAll();

    ClubDto save(ClubDto club);

    ClubDto findById(long id) throws NotFoundException;

    void update(ClubDto club) throws NotFoundException;

    void deleteById(Long id);
}
