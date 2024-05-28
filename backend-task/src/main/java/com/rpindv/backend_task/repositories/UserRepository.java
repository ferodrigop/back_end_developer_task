package com.rpindv.backend_task.repositories;

import com.rpindv.backend_task.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
