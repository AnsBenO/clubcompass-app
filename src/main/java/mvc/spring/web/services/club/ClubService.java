package mvc.spring.web.services.club;

import mvc.spring.web.dto.ClubDto;
import mvc.spring.web.services.common.CrudService;
import mvc.spring.web.services.common.Searchable;

public interface ClubService extends CrudService<ClubDto, Long>, Searchable<ClubDto> {

}
