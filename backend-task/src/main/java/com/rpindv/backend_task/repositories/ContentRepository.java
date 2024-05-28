package com.rpindv.backend_task.repositories;

import com.rpindv.backend_task.entities.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {
}
