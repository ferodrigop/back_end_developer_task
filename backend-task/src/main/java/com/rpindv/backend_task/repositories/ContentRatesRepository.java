package com.rpindv.backend_task.repositories;

import com.rpindv.backend_task.entities.ContentRates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContentRatesRepository extends JpaRepository<ContentRates, Long> {
    @Query(value = """
        SELECT AVG(cr.rating) as overall
        FROM content_rates cr
        INNER JOIN content con ON con.id_content = con.id_content
        LEFT JOIN users us ON us.id_user = cr.id_user
        WHERE cr.id_content = :id
        """, nativeQuery = true)
    String getAverageContentRate(@Param("id") Long id);
}
