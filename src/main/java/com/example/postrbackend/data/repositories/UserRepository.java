package com.example.postrbackend.data.repositories;

import com.example.postrbackend.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
	User findByFirebaseUid(String username);

	@Query(value = "select * from users where user_id in\n" +
			"((SELECT user_id_2 as user_id from messages where user_id_1 = ?1)\n" +
			"UNION \n" +
			"(SELECT user_id_1 as user_id from messages where user_id_2 = ?1))",nativeQuery = true)
	List<User> findAllAuthors(int id);
}
