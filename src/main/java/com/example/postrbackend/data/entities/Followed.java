package com.example.postrbackend.data.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "followed")
@Entity
@Data
public class Followed implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, name = "follow_id", nullable = false)
    private Integer followId;

    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "user_id")
    private Integer userId;

    
}