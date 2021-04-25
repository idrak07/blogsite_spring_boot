package com.myblog.intern.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "post_sequence")
public class PostSequence {
    @Id
    @Column(name = "next_val")
    private Integer nextVal;
}
