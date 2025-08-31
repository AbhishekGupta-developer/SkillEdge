package com.myorganisation.SkillEdge.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found");
    }

    public UserNotFoundException(String m) {
        super(m);
    }
}
