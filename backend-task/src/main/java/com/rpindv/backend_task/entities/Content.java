package com.rpindv.backend_task.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rpindv.backend_task.helpers.validators.custom_anotations.NotNullOrBlank;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "content")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Content {
    @Id
    @SequenceGenerator(name = "contentIdSeq", sequenceName = "contentIdSeq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contentIdSeq")
    @Column(name = "id_content", updatable = false, unique = true, nullable = false)
    private Long id_content;

    @NotNullOrBlank(message = "Please provide a Title")
    @Size(max = 200)
    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @NotNullOrBlank(message = "Please provide a Description")
    @Size(max = 255)
    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @NotNullOrBlank
    @Size(max = 300)
    @Column(name = "content_url", nullable = false, unique = true, length = 300)
    private String content_url;

    @NotNullOrBlank
    @Size(max = 300)
    @Column(name = "content_path", nullable = false, length = 300)
    private String content_path;

    @NotNullOrBlank
    @Size(max = 200)
    @Column(name = "content_name", nullable = false, length = 300)
    private String content_name;

    @NotNullOrBlank
    @Size(max = 20)
    @Column(name = "content_extension", nullable = false, length = 300)
    private String content_extension;

    @NotNullOrBlank
    @Size(max = 40)
    @Column(name = "content_type", nullable = false, length = 300)
    private String content_type;

    @NotNullOrBlank
    @Size(max = 300)
    @Column(name = "thumbnail_url", nullable = false, length = 300)
    private String thumbnail_url;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "id_category_content", referencedColumnName = "id_category_content")
    private CategoryContent id_category_content;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "created_by", referencedColumnName = "id_user")
    private User created_by;

    @NotNull
    @Column(name = "created_at")
    private LocalDateTime created_at;
}
