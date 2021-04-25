package com.myblog.intern.repository;

import com.myblog.intern.model.PostSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostSequenceRepository extends JpaRepository<PostSequence,Integer> {
    @Query(
            value = "select next_val from post_sequence" ,
            nativeQuery = true
    )
    Integer findNextVal();
}
