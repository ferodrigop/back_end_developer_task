package com.rpindv.backend_task.repositories;

import com.rpindv.backend_task.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = """
        SELECT *
        FROM users
        WHERE email_user = :email_user
        """, nativeQuery = true)
    Optional<User> findByEmail(@Param("email_user") String email_user);
}
