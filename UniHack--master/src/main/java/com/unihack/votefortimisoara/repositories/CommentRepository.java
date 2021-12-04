package com.unihack.votefortimisoara.repositories;

import com.unihack.votefortimisoara.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/3/2021, Fri
 **/
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
