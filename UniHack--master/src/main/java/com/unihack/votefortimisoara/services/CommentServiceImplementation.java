package com.unihack.votefortimisoara.services;

import com.unihack.votefortimisoara.entities.Comment;
import com.unihack.votefortimisoara.repositories.CommentRepository;
import com.unihack.votefortimisoara.services.interfaces.RestInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Onetiu-George
 * @since : 12/3/2021, Fri
 **/
@Service
public class CommentServiceImplementation implements RestInterface<Comment> {

    private final CommentRepository commentRepository;
    private final RestService<Comment> restService;

    public CommentServiceImplementation(final CommentRepository commentRepository, final RestService<Comment> restService) {
        this.commentRepository = commentRepository;
        this.restService = restService;
    }

    @Override
    public ResponseEntity<Object> createEntity(Comment Comment) {
        return restService.createEntity(Comment, commentRepository);
    }

    @Override
    public ResponseEntity<Object> createEntities(List<Comment> Comments) {
        return restService.createEntities(Comments, commentRepository);
    }

    @Override
    public Comment getEntityById(Long id) {
        return restService.getEntityById(id, commentRepository, Comment.class);
    }

    @Override
    public List<Comment> getEntitiesByIdList(List<Long> ids) {
        return restService.getEntitiesByIdList(ids, commentRepository, Comment.class);
    }

    @Override
    public List<Comment> getAllEntities() {
        return restService.getAllEntities(commentRepository, Comment.class);
    }

    @Override
    public Comment updateEntityById(final Long id, final Comment comment) throws IllegalAccessException {
        if (commentRepository.findById(id).isPresent()) {
            final Comment commentToUpdate = commentRepository.findById(id).get();

            return restService.updateEntity(comment, commentToUpdate, commentRepository);
        }
        return null;
    }

    @Override
    public void deleteAllEntities() {
        restService.deleteAllEntities(commentRepository, Comment.class);
    }

    @Override
    public boolean deleteEntityById(Long id) {
        return restService.deleteEntityById(id, commentRepository, Comment.class);
    }
}