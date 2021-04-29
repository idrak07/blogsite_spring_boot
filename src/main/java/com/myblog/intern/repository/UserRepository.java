package com.myblog.intern.repository;

import com.myblog.intern.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUserName(String userName);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Integer userId);
    List<User> findAllByRole(String role);
}
