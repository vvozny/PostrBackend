package com.example.postrbackend.data.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Table(name = "events")
@Data
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", insertable = false, nullable = false)
    private Integer eventId;

    @Column(name = "event_name", nullable = false)
    private String eventName;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "creation_timestamp", nullable = false)
    private Timestamp creationTimestamp ;

    @Column(name = "expiration_timestamp", nullable = false)
    private Timestamp expirationTimestamp;

    @Column(name = "content")
    private String content;

    @Column(name = "image")
    private byte[] image;

    @OneToMany
    @JoinColumn(name="event_id")
    List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}