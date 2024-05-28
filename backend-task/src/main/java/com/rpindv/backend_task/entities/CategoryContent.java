package com.rpindv.backend_task.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rpindv.backend_task.helpers.validators.custom_anotations.NotNullOrBlank;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category_content")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CategoryContent {
    @Id
    @SequenceGenerator(name = "category_contentIdSeq", sequenceName = "category_contentIdSeq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_contentIdSeq")
    @Column(name = "id_category_content", updatable = false, unique = true, nullable = false)
    private Integer id_category_content;

    @NotNullOrBlank
    @Size(max = 100)
    @Column(name = "category_content", nullable = false, length = 100)
    private String category_content;
}
