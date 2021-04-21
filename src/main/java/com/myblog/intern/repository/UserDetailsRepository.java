package com.myblog.intern.repository;

import com.myblog.intern.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {
    UserDetails findByUserId(Integer userId);
}
