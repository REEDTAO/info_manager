package com.reed.info_manager.entity;


import lombok.Data;

@Data
public class User{
    Integer id;
    String name;
    String password;
    String addr;
    String email;
    String tel;
    String sex;
}