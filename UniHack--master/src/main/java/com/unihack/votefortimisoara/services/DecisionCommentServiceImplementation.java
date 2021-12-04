package com.unihack.votefortimisoara.services;

import com.unihack.votefortimisoara.entities.Comment;
import com.unihack.votefortimisoara.entities.Decision;
import com.unihack.votefortimisoara.repositories.CommentRepository;
import com.unihack.votefortimisoara.repositories.DecisionRepository;
import com.unihack.votefortimisoara.services.interfaces.EnhancedRestInterface;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Onetiu-George
 * @since : 12/4/2021, Sat
 **/
@Service
public class DecisionCommentServiceImplementation implements EnhancedRestInterface<Decision, Comment> {

    private final DecisionRepository decisionRepository;
    private final CommentRepository commentRepository;
    private final EnhancedRestService<Decision, Comment> enhancedRestService;

    public DecisionCommentServiceImplementation(final DecisionRepository decisionRepository, final EnhancedRestService<Decision, Comment> enhancedRestService, final CommentRepository commentRepository) {
        this.decisionRepository = decisionRepository;
        this.enhancedRestService = enhancedRestService;
        this.commentRepository = commentRepository;
    }

    @Override
    public Decision addChildToParent(Long parentId, Long childId) throws IllegalAccessException, NoSuchFieldException {
        return enhancedRestService.addChildToParent(parentId, childId, decisionRepository, commentRepository, "comments");
    }

    @Override
    public Decision addChildrenToParent(Long parentId, List<Long> childrenIds) throws IllegalAccessException, NoSuchFieldException {
        return enhancedRestService.addChildrenToParent(parentId, childrenIds, decisionRepository, commentRepository, "comments");
    }

    @Override
    public Comment getChildFromParent(Long parentId, Long childId) throws IllegalAccessException, NoSuchFieldException {
        return enhancedRestService.getChildFromParent(parentId, childId, Comment.class, decisionRepository, "comments");
    }

    @Override
    public List<Comment> getChildrenFromParent(Long parentId) throws IllegalAccessException, NoSuchFieldException {
        return enhancedRestService.getChildrenFromParent(parentId, Comment.class, decisionRepository, "comments");
    }
}
