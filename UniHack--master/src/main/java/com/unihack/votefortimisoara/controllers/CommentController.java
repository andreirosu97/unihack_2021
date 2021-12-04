package com.unihack.votefortimisoara.controllers;

import com.unihack.votefortimisoara.controllers.interfaces.EntityControllerInterface;
import com.unihack.votefortimisoara.entities.Comment;
import com.unihack.votefortimisoara.services.CommentServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Onetiu-George
 * @since : 12/3/2021, Fri
 **/
@RestController
@RequestMapping(value = "/comment")
public class CommentController implements EntityControllerInterface<Comment> {

    private final CommentServiceImplementation commentServiceImplementation;

    @Autowired
    public CommentController(final CommentServiceImplementation commentServiceImplementation) {
        this.commentServiceImplementation = commentServiceImplementation;
    }

    // CREATE
    @PostMapping("")
    @Override
    public ResponseEntity<Object> createEntity(Comment entity) {
        return commentServiceImplementation.createEntity(entity);
    }

    @PostMapping("/l")
    @Override
    public ResponseEntity<Object> createEntities(List<Comment> entities) {
        return commentServiceImplementation.createEntities(entities);
    }

    // READ
    @GetMapping("{id}")
    @Override
    public Comment getEntityById(@PathVariable Long id) {
        return commentServiceImplementation.getEntityById(id);
    }

    @GetMapping("/l")
    @Override
    public List<Comment> getEntityByIdList(List<Long> ids) {
        return commentServiceImplementation.getEntitiesByIdList(ids);
    }

    @GetMapping("")
    @Override
    public List<Comment> getAllEntities() {
        return commentServiceImplementation.getAllEntities();
    }

    // UPDATE
    @PutMapping("/{id}")
    @Override
    public Comment updateEntityById(@PathVariable Long id, @RequestBody Comment entity) throws IllegalAccessException {
        return commentServiceImplementation.updateEntityById(id,entity);
    }

    // DELETE
    @DeleteMapping("")
    @Override
    public ResponseEntity<String> deleteAllEntities() {
        commentServiceImplementation.deleteAllEntities();
        return new ResponseEntity<>("Deleted all Decisions!", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Override
    public ResponseEntity<String> deleteEntityById(@PathVariable Long id) {
        final boolean isPresent = commentServiceImplementation.deleteEntityById(id);

        if (isPresent) {
            return new ResponseEntity<>("Deleted Decision with id " + id, HttpStatus.OK);
        }
        return new ResponseEntity<>("Id is non existent", HttpStatus.BAD_REQUEST);
    }
}
