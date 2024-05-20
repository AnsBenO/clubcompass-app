package org.clubcompass.app.services.common;

import java.util.List;

public interface Searchable<T> {
    List<T> search(String query);
}
