package com.unihack.votefortimisoara.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @since : 12/3/2021, Fri
 **/

@MappedSuperclass
@Getter
@Setter
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -6401694946281957165L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @SequenceGenerator(name = "generator", sequenceName = "seq")
    @Column(name = "ID")
    private long id;

    @Version
    @Column(name = "DATABASE_VERSION")
    private Long databaseVersion;

    /**
     * Time of entity creation.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_ON")
    private Date createdOn = new Date(System.currentTimeMillis());

    /**
     * Time of last update of the entity
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_ON")
    private Date updatedOn = new Date(System.currentTimeMillis());


    @PreUpdate
    public void preUpdate() {
        updatedOn = new Date(System.currentTimeMillis());
    }
}
