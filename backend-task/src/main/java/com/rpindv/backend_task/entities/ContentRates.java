package com.rpindv.backend_task.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rpindv.backend_task.helpers.validators.custom_anotations.NotNullOrBlank;
import com.rpindv.backend_task.helpers.validators.custom_anotations.RatingsValue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "content_rates")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ContentRates {
    @Id
    @SequenceGenerator(name = "contentRateIdSeq", sequenceName = "contentRateIdSeq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contentRateIdSeq")
    @Column(name = "id_content_rate", updatable = false, unique = true, nullable = false)
    private Long id_content_rate;

    @NotNullOrBlank
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_content", referencedColumnName = "id_content", nullable = false)
    private Content id_content;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User id_user;

    @RatingsValue
    @NotNullOrBlank
    @Column(name = "rating")
    private Double rating;
}
