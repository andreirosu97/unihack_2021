package com.unihack.votefortimisoara.services;

import com.unihack.votefortimisoara.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

/**
 * @author : Onetiu-George
 * @since : 12/4/2021, Sat
 **/
@Service
@Transactional
public class EnhancedRestService<T, K extends BaseEntity> {

    public T addChildToParent(final Long parentId, final Long childId, final JpaRepository<T, Long> parentRepo, final JpaRepository<K, Long> childRepo, final String fieldName) throws IllegalAccessException, NoSuchFieldException {
        final Optional<T> foundParent = parentRepo.findById(parentId);
        final Optional<K> foundChild = childRepo.findById(childId);

        if (foundParent.isPresent() && foundChild.isPresent()) {
            final T parent = foundParent.get();
            final K child = foundChild.get();
            final Field field = parent.getClass().getDeclaredField(fieldName);

            field.setAccessible(true);
            List<K> children = (List<K>) field.get(parent);
            children.add(child);
            field.set(parent, children);
            return parentRepo.save(parent);
        }
        return null;

    }

    public T addChildrenToParent(final Long parentId, final List<Long> childrenIds, final JpaRepository<T, Long> parentRepo, final JpaRepository<K, Long> childRepo, final String fieldName) throws IllegalAccessException, NoSuchFieldException {
        final Optional<T> foundParent = parentRepo.findById(parentId);
        final List<K> foundChildren = childRepo.findAllById(childrenIds);

        if(foundParent.isPresent() && !foundChildren.isEmpty()) {
            final T parent = foundParent.get();
            final Field field = parent.getClass().getDeclaredField(fieldName);

            field.setAccessible(true);
            final List<K> parentChildren = (List<K>) field.get(parent);
            parentChildren.addAll(foundChildren);
            return parentRepo.save(parent);
        }
        return null;
    }

    public K getChildFromParent(final Long parentId, final Long id, final Class<K> childClass, final JpaRepository<T, Long> parentRepo, final String fieldName) throws IllegalAccessException, NoSuchFieldException {
        final Optional<T> foundParent = parentRepo.findById(parentId);

        if(foundParent.isPresent()) {
            final T parent = foundParent.get();
            final Field field = parent.getClass().getDeclaredField(fieldName);

            field.setAccessible(true);
            final List<K> children = (List<K>) field.get(parent);
            final Optional<K> foundChild = children.stream().filter(child -> child.getId() == id).findFirst();
            return foundChild.orElse(null);
        }
        return null;
    }

    public List<K> getChildrenFromParent(final Long parentId, final Class<K> childClass, final JpaRepository<T, Long> parentRepo, final String fieldName) throws IllegalAccessException, NoSuchFieldException {
        final Optional<T> foundParent = parentRepo.findById(parentId);

        if(foundParent.isPresent()) {
            final T parent = foundParent.get();
            final Field field = parent.getClass().getDeclaredField(fieldName);

            field.setAccessible(true);
            field.setAccessible(true);
            return (List<K>) field.get(parent);
        }
        return null;
    }
}
