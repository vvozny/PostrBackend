package com.example.postrbackend.data.repositories;

import com.example.postrbackend.data.entities.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer>, JpaSpecificationExecutor<Message> {
	@Query(value="Select * from messages where user_id_1 = ?1 or user_id_2 = ?1 order by send_timestamp desc",nativeQuery=true)
	List<Message> findAllByUserId(int id, Pageable page);

	@Query(value="select * from\n" +
			"(\n" +
			"(select * from messages where user_id_1 = ?1 and user_id_2 = ?2 order by send_timestamp desc limit 1)\n" +
			"union\n" +
			"(select * from messages where user_id_2 = ?1 and user_id_1 = ?2 order by send_timestamp desc limit 1)\n" +
			") as last_message\n" +
			"order by send_timestamp desc limit 1",nativeQuery=true)
	Message findLastMessage(int id,int id2);

	@Query(value="(Select * from messages where user_id_1 = ?1 and user_id_2 = ?2)\n" +
			"union\n" +
			"(Select * from messages where user_id_1 = ?2 and user_id_2 = ?1)\n" +
			"order by send_timestamp desc",nativeQuery=true)
	List<Message> findBetweenUsers(int id1, int id2);

	@Query(value="select * from\n" +
			"(\n" +
			"(Select * from messages where user_id_1 = ?1 and user_id_2 = ?2)\n" +
			"union\n" +
			"(Select * from messages where user_id_1 = ?2 and user_id_2 = ?1)\n" +
			") as messages\n" +
			"where message_id > ?3 \n" +
			"order by send_timestamp desc",nativeQuery=true)
	List<Message> findBetweenUsers(int id1,int id2,int messageId);

}