package io.github.jhlee.Model;

import lombok.Data;

@Data
public class User {

    private String id;
    private String email;
    private String password;
    private String name;
    private int age;

}
