package com.unihack.votefortimisoara.controllers;

import com.unihack.votefortimisoara.entities.Comment;
import com.unihack.votefortimisoara.entities.Decision;
import com.unihack.votefortimisoara.services.DecisionCommentServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Onetiu-George
 * @since : 12/3/2021, Fri
 **/
@RestController
@RequestMapping(value = "/decision/{decisionId}/comment")
public class DecisionCommentController {

    private final DecisionCommentServiceImplementation serviceImplementation;

    @Autowired
    public DecisionCommentController(final DecisionCommentServiceImplementation serviceImplementation) {
        this.serviceImplementation = serviceImplementation;
    }

    @PutMapping("/{commentId}")
    public Decision addChildToParent(@PathVariable("decisionId") final Long decisionId, @PathVariable("commentId") final Long commentId) throws IllegalAccessException, NoSuchFieldException {
        return serviceImplementation.addChildToParent(decisionId, commentId);
    }

    @PutMapping("")
    public Decision addChildrenToParent(@PathVariable("decisionId") final Long decisionId, @RequestBody final List<Long> childrenIds) throws IllegalAccessException, NoSuchFieldException {
        return serviceImplementation.addChildrenToParent(decisionId, childrenIds);
    }

    @GetMapping("/{commentId}")
    public Comment getChildFromParent(@PathVariable("decisionId") final Long decisionId, @PathVariable("commentId") final Long commentId) throws IllegalAccessException, NoSuchFieldException {
        return serviceImplementation.getChildFromParent(decisionId, commentId);
    }

    @GetMapping("")
    public List<Comment> getChildrenFromParent(@PathVariable("decisionId") final Long decisionId) throws IllegalAccessException, NoSuchFieldException {
        return serviceImplementation.getChildrenFromParent(decisionId);
    }
}
