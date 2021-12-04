package com.unihack.votefortimisoara.controllers;

import com.unihack.votefortimisoara.controllers.interfaces.EntityControllerInterface;
import com.unihack.votefortimisoara.entities.User;
import com.unihack.votefortimisoara.services.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author : Onetiu-George
 * @since : 12/3/2021, Fri
 **/
@RestController
@RequestMapping(value = "/user")
public class UserController implements EntityControllerInterface<User> {

    private final UserServiceImplementation userServiceImplementation;

    @Autowired
    public UserController(final UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }

    // CREATE
    @PostMapping("")
    @Override
    public ResponseEntity<Object> createEntity(@RequestBody final User user) {
        return userServiceImplementation.createEntity(user);
    }

    @PostMapping("/l")
    @Override
    public ResponseEntity<Object> createEntities(@Valid @RequestBody final List<User> users) {
        return userServiceImplementation.createEntities(users);
    }

    // READ
    @GetMapping("/{id}")
    @Override
    public User getEntityById(@PathVariable final Long id) {
        return userServiceImplementation.getEntityById(id);
    }

    @GetMapping("/l")
    @Override
    public List<User> getEntityByIdList(@RequestBody final List<Long> ids) {
        return userServiceImplementation.getEntitiesByIdList(ids);
    }

    @GetMapping("")
    @Override
    public List<User> getAllEntities() {
        return userServiceImplementation.getAllEntities();
    }

    // UPDATE
    @PutMapping("/{id}")
    @Override
    public User updateEntityById(@PathVariable final Long id, @RequestBody final User user) throws IllegalAccessException {
        return userServiceImplementation.updateEntityById(id, user);
    }

    // DELETE
    @DeleteMapping("")
    @Override
    public ResponseEntity<String> deleteAllEntities() {
        userServiceImplementation.deleteAllEntities();
        return new ResponseEntity<>("Deleted all users!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEntityById(@PathVariable final Long id) {
        final boolean isPresent = userServiceImplementation.deleteEntityById(id);

        if (isPresent) {
            return new ResponseEntity<>("Deleted user with id " + id, HttpStatus.OK);
        }
        return new ResponseEntity<>("Id is non existent", HttpStatus.BAD_REQUEST);
    }
}
