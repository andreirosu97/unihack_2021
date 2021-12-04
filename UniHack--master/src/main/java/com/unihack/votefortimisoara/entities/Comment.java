package com.unihack.votefortimisoara.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author : Onetiu-George
 * @since : 12/3/2021, Fri
 **/
@Entity
@Getter
@Setter
@Table(name = "COMMENT")
public class Comment extends BaseEntity{

    private static final long serialVersionUID = -6091889729970424328L;

    @Column(name = "TEXT")
    private String text;

    @ManyToOne
    private User user;

    @ManyToOne
    @JsonBackReference
    private Decision decision;
}
