package com.unihack.votefortimisoara.services.interfaces;

import com.unihack.votefortimisoara.entities.BaseEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @since : 12/3/2021, Fri
 **/

public interface RestInterface<T extends BaseEntity> {
    ResponseEntity<Object> createEntity(final T entity);
    ResponseEntity<Object> createEntities(final List<T> entities);
    T getEntityById(final Long id);
    List<T> getEntitiesByIdList(final List<Long> ids);
    List<T> getAllEntities();
    T updateEntityById(final Long id, final T entity) throws IllegalAccessException;
    void deleteAllEntities();
    boolean deleteEntityById(final Long id);
}
