package com.unihack.votefortimisoara.controllers.interfaces;

import com.unihack.votefortimisoara.entities.BaseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author : Onetiu-George
 * @since : 12/3/2021, Fri
 **/

public interface EntityControllerInterface<T extends BaseEntity> {

    // CREATE
    ResponseEntity<Object> createEntity(@RequestBody final T entity);
    ResponseEntity<Object> createEntities(@RequestBody final List<T> entities);
    // GET
    T getEntityById(final Long id);
    List<T> getEntityByIdList(final List<Long> ids);
    List<T> getAllEntities();
    // UPDATE
    T updateEntityById(final Long id,final T entity) throws IllegalAccessException;
    // DELETE
    public ResponseEntity<String> deleteAllEntities();
    public ResponseEntity<String> deleteEntityById(final Long id);
}
