package com.example.postrbackend.data.repositories;

import com.example.postrbackend.data.entities.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.example.postrbackend.data.entities.Followed;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowedRepository extends JpaRepository<Follow, Integer>, JpaSpecificationExecutor<Followed> {
	List<Follow> findAllByEventEventId(Integer eventId);

	@Query(value = "delete from followed where user_id=?1 and event_id=?2",nativeQuery=true)
	Follow deleteByIds(Integer userId,Integer eventId);
}