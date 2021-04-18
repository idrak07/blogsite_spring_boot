package com.myblog.intern.repository;

import com.myblog.intern.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChangePasswordRepository extends JpaRepository<User, Long> {
    User getByPassword(String password);
}
