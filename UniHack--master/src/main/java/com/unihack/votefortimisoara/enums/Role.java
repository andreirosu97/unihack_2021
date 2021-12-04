package com.unihack.votefortimisoara.enums;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/3/2021, Fri
 **/

public enum Role {
    
    CITIZEN("Citizen"),
    MODERATOR("Moderator");

    private final String name;

    Role(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
