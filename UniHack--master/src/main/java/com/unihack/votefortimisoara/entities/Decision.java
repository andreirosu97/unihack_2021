package com.unihack.votefortimisoara.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/3/2021, Fri
 **/
@Entity
@Getter
@Setter
@Table(name = "DECISION")
public class Decision extends BaseEntity{

    @Column(name = "DESCRIPTION", length = 256, nullable = false, unique = true)
    @NotNull
    private String description;

    @Column(name = "DECISION_NUMBER", length = 256, nullable = false)
    @NotNull
    private int decisionNumber;

    @Column(name = "REQUEST_ID")
    @NotNull
    private long decisionRequestId;

    @Column(name = "PUBLISHER")
    @NotNull
    private String publisher;

    @OneToMany(mappedBy = "decision")
    @JsonManagedReference
    private List<Comment> comments;
}
