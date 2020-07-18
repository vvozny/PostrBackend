package com.example.postrbackend.data.repositories;

import com.example.postrbackend.data.entities.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface EventRepository extends JpaRepository<Event, Integer>, JpaSpecificationExecutor<Event> {

	@Query(value="Select * from events where creation_timestamp < expiration_timestamp order by event_id desc",nativeQuery=true)
	List<Event> findAllByCreationTimestampBeforeExpirationTimestamp(Pageable pageable);
}