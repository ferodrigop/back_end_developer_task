package com.rpindv.backend_task.repositories;

import com.rpindv.backend_task.entities.Content;
import com.rpindv.backend_task.entities.ContentRates;
import com.rpindv.backend_task.entities.User;
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

    @Query(value = """
    SELECT CASE WHEN COUNT(1) > 0 THEN TRUE ELSE FALSE END FROM content_rates cr WHERE cr.id_content = :id_content AND cr.id_user = :id_user
    """, nativeQuery = true)
    boolean checkIfAlreadyRated(@Param("id_content") Long idContent, @Param("id_user") Integer idUser);
}
