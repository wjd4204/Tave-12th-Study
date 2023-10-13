package com.practice.taveboard.user.repository;

import com.practice.taveboard.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {


    Optional<User> findByUsername(String username);

    Optional<User> findByNickname(String nickname);

}
