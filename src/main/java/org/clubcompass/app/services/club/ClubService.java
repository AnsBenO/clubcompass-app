package org.clubcompass.app.services.club;

import java.util.List;

import org.clubcompass.app.dto.ClubDto;
import org.clubcompass.app.services.common.Searchable;

import javassist.NotFoundException;

public interface ClubService extends Searchable<ClubDto> {
    List<ClubDto> findAll();

    ClubDto save(ClubDto club);

    ClubDto findById(long id) throws NotFoundException;

    void update(ClubDto club) throws NotFoundException;

    void deleteById(Long id);
}
