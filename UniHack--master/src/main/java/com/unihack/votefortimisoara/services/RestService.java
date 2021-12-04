package com.unihack.votefortimisoara.services;

import com.unihack.votefortimisoara.entities.BaseEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/3/2021, Fri
 **/

@Service
@Log4j2
public class RestService<T extends BaseEntity> {

    private static final List<String> ignoredFields = new ArrayList<>();

    static {
        ignoredFields.add("serialVersionUID");
    }

    // ------------------- CREATE -------------------
    public ResponseEntity<Object> createEntity(final T entity, final JpaRepository<T, Long> repo) {
        try {
            final T savedEntity = repo.save(entity);
            log.info("[POST] Creating entity " + savedEntity.getClass().getSimpleName() + ":" + savedEntity.getId());

            final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(savedEntity.getId()).toUri();

            return ResponseEntity
                    .created(location)
                    .build();
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(e.getMostSpecificCause().getMessage(), HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<Object> createEntities(final List<T> entities, final JpaRepository<T, Long> repo) {
        try {
            final Set<Long> savedIds = new HashSet<>();
            repo.saveAll(entities).forEach(entity -> {
                log.info("[POST] Creating entity " + entity.getClass().getSimpleName() + ":" + entity.getId());
                savedIds.add(entity.getId());
            });

            final URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

            return ResponseEntity
                    .created(location)
                    .header("Saved-Ids", savedIds.toString())
                    .build();
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(e.getMostSpecificCause().getMessage(), HttpStatus.CONFLICT);
        }
    }

    // ------------------- GET -------------------
    public T getEntityById(final Long id, final JpaRepository<T, Long> repo, final Class<T> classType) {

        if (repo.findById(id).isPresent()) {
            final T foundEntity = repo.findById(id).get();
            log.info("[GET] " + foundEntity.getClass().getSimpleName() + ":" + id);
            return foundEntity;
        }
        log.warn("[GET] " + " Id is not present for " + classType.getSimpleName() + ":" + id);
        return null;
    }

    public List<T> getEntitiesByIdList(final List<Long> ids, final JpaRepository<T, Long> repo, Class<T> classType) {
        final List<T> toReturn = new ArrayList<>();

        ids.forEach(id -> {
            if (repo.findById(id).isPresent()) {
                final T foundEntity = repo.findById(id).get();
                log.info("[GET] " + foundEntity.getClass().getSimpleName() + ":" + id);
                toReturn.add(foundEntity);
            } else {
                log.warn("[GET]" + "Id is not present for " + classType.getSimpleName() + ":" + id);
            }
        });

        return toReturn;
    }

    public List<T> getAllEntities(final JpaRepository<T, Long> repo, Class<T> classType) {
        log.info("[GET] Fetching all entities of type " + classType.getSimpleName());
        return repo.findAll();
    }

    // ------------------- UPDATE -------------------
    public T updateEntity(final T source, final T destination, final JpaRepository<T, Long> repo) throws IllegalAccessException {
        log.info("[PATCH] " + source.getClass().getSimpleName() + ":" + destination.getId());
        updateFields(source, destination);
        return repo.save(destination);
    }

    private void updateFields(final Object source, final Object destination) throws IllegalAccessException {
        Field[] fields = source.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (!ignoredFields.contains(field.getName())) {
                field.setAccessible(true);
                field.set(destination, field.get(source));
            }
        }
    }

    // ------------------- DELETE -------------------
    public void deleteAllEntities(final JpaRepository<T, Long> repo, final Class<T> classType) {
        log.warn("[DELETE] Deleting all entities of type " + classType.getSimpleName());
        repo.deleteAll();
    }

    public boolean deleteEntityById(final Long id, final JpaRepository<T, Long> repo, final Class<T> classType) {
        // If the entity with the given id is not present, then return false
        if (repo.findById(id).isEmpty()) {
            log.warn("[DELETE] Could not find entity " + classType.getSimpleName() + ":" + id);
            return false;
        }
        log.info("[DELETE] Deleted " + classType.getSimpleName() + ":" + id);
        repo.deleteById(id);
        return true;
    }
}
