package io.github.jhlee.model;

import lombok.Data;

@Data
public class User {

    private String id;
    private String email;
    private String password;
    private String name;
    private int age;

}
