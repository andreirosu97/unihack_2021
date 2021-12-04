package com.unihack.votefortimisoara.entities;

import com.unihack.votefortimisoara.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author : Onetiu-George
 * @since : 12/3/2021, Fri
 **/
@Entity
@Getter
@Setter
@Table(name = "USER")
public class User extends BaseEntity{

    private static final long serialVersionUID = -6091889729860424328L;

    @Column(name = "USERNAME", length = 64, nullable = false, unique = true)
    @NotNull
    private String username;

    @Column(name = "NAME", length = 64, nullable = false)
    @NotNull
    private String name;

    @Column(name = "SURNAME", length = 64, nullable = false)
    @NotNull
    private String surname;

    @Column(name = "MIDDLE_NAME", length = 64, nullable = false)
    private String middleName;

    @Column(name = "EMAIL", length = 254, nullable = false, unique = true)
    @NotNull
    private String email;

    @Column(name = "ROLE")
    @NotNull
    private Role role;

    @Column(name = "PASSWORD", length = 254, nullable = false)
    private String password;
}
