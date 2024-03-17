package mvc.spring.web.services;

import java.util.List;

import javassist.NotFoundException;
import mvc.spring.web.dto.ClubDto;

public interface CrudService<T, ID> {

    List<T> findAll();

    T save(ClubDto club);

    T findById(long id) throws NotFoundException;

    void update(T object);

    void deleteById(ID id);

}
