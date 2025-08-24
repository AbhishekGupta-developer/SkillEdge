package com.myorganisation.SkillEdge.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException() {
        super("Student not found");
    }

    public StudentNotFoundException(String m) {
        super(m);
    }
}
