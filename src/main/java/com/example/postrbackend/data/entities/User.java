package com.example.postrbackend.data.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "name")
    private String name;

    @Column(name = "range")
    private Integer range;

    @Column(name = "creation_timestamp")
    private Timestamp creationTimestamp;

    @Column(name = "firebase_uid")
    private String firebaseUid;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "avatar")
    private byte[] avatar;

    @Column(name="user_longitude")
    private Double userLongitude;

    @Column(name="user_latitude")
    private Double userLatitude;

    @Column(name = "firebase_reg_token")
    private String firebaseRegToken;

}