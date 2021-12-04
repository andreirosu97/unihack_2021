package com.unihack.votefortimisoara.repositories;

import com.unihack.votefortimisoara.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Onetiu-George
 * @since : 12/3/2021, Fri
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
