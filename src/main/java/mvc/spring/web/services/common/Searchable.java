package mvc.spring.web.services.common;

import java.util.List;

public interface Searchable<T> {
    List<T> search(String query);
}
