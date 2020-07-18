package com.example.postrbackend.data.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "messages")
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id", insertable = false, nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id_1")
    User user1;

    @ManyToOne
    @JoinColumn(name = "user_id_2")
    User user2;

    @Column(name = "content")
    private String content;

    @Column(name = "send_timestamp")
    private Timestamp sendTimestamp;

    @Column(name = "status_id", nullable = false)
    private Integer statusId;

    
}