package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "user")
@Data
public class User extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int userId;
    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "status_user")
    private int statusUser;
    @Column(name = "name")
    private String name;
    @Column(name = "dob")
    private Date dob;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String diachi;

    @Column(name = "role_id")
    private int roleId;
    @Column(name = "session")
    private String session;
    @Column(name = "token")
    private String token;
    @Column(name = "avatar")
    private String avatar;


}
