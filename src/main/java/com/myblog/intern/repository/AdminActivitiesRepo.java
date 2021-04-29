package com.myblog.intern.repository;

import com.myblog.intern.model.AdminActivities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminActivitiesRepo  extends JpaRepository<AdminActivities,Integer> {
    List<AdminActivities> findByAdminId(int adminId);
}
