package com.example.postrbackend.data.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "followed")
@Entity
@Data
public class Follow implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, name = "follow_id", nullable = false)
	private Integer followId;

	@ManyToOne
	@JoinColumn(name="user_id")
	User user;

	@OneToOne
	@JoinColumn(name = "event_id")
	Event event;
}