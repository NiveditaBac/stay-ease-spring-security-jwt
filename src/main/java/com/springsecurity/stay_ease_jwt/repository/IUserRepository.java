package com.springsecurity.stay_ease_jwt.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.springsecurity.stay_ease_jwt.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email); 

}
