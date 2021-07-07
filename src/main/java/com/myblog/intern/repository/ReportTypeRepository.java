package com.myblog.intern.repository;

import com.myblog.intern.model.ReportType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReportTypeRepository extends JpaRepository<ReportType, Integer> {
    @Override
    Optional<ReportType> findById(Integer integer);

    @Override
    boolean existsById(Integer typeId);
}
