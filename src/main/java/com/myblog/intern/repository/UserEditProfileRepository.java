package com.myblog.intern.repository;
import com.myblog.intern.model.User;
import com.myblog.intern.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserEditProfileRepository extends JpaRepository<UserDetails,Integer> {

    List<UserDetails> findAllByRole(String role);
    UserDetails findByUserId(int id);
//    @Modifying
//    @Transactional
//    @Query(value = "update user u inner join user_details s on u.user_id = s.user_id=? set u.email = s.email,u.role=s.role",nativeQuery = true)
//    int updateUserDetailInteger(int id);




}
