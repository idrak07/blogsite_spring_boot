package com.myblog.intern.repository;
import com.myblog.intern.model.User;
import com.myblog.intern.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface UserEditProfileRepository extends JpaRepository<UserDetails,Integer> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE user d SET d.user_name=?,d.email=?",nativeQuery = true)
    int updateUserDetailInteger(String firsName, String email);


}
