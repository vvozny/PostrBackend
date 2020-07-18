package com.example.postrbackend.data.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Table(name = "comments")
@Entity
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", insertable = false, nullable = false)
    private Integer commentId;

    @Column(name = "event_id", nullable = false)
    private Integer eventId;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "creation_timestamp", nullable = false)
    private Timestamp creationTimestamp ;

    @ManyToOne
    @JoinColumn(name="user_id")
    User user;
}