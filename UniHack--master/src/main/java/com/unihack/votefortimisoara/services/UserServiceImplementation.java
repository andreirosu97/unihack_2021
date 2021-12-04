package com.unihack.votefortimisoara.services;

import com.unihack.votefortimisoara.entities.User;
import com.unihack.votefortimisoara.repositories.UserRepository;
import com.unihack.votefortimisoara.services.interfaces.RestInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/3/2021, Fri
 **/
@Service
public class UserServiceImplementation implements RestInterface<User> {

    private final UserRepository userRepository;
    private final RestService<User> restService;

    public UserServiceImplementation(final UserRepository userRepository, final RestService<User> restService) {
        this.userRepository = userRepository;
        this.restService = restService;
    }

    @Override
    public ResponseEntity<Object> createEntity(User user) {
        return restService.createEntity(user, userRepository);
    }

    @Override
    public ResponseEntity<Object> createEntities(List<User> users) {
        return restService.createEntities(users, userRepository);
    }

    @Override
    public User getEntityById(Long id) {
        return restService.getEntityById(id, userRepository, User.class);
    }

    @Override
    public List<User> getEntitiesByIdList(List<Long> ids) {
        return restService.getEntitiesByIdList(ids, userRepository, User.class);
    }

    @Override
    public List<User> getAllEntities() {
        return restService.getAllEntities(userRepository, User.class);
    }

    @Override
    public User updateEntityById(Long id, User user) throws IllegalAccessException {
        if (userRepository.findById(id).isPresent()) {
            final User userToUpdate = userRepository.findById(id).get();

            return restService.updateEntity(user, userToUpdate, userRepository);
        }
        return null;
    }

    @Override
    public void deleteAllEntities() {
        restService.deleteAllEntities(userRepository, User.class);
    }

    @Override
    public boolean deleteEntityById(Long id) {
        return restService.deleteEntityById(id, userRepository, User.class);
    }
}
