package com.rpindv.backend_task.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rpindv.backend_task.helpers.validators.custom_anotations.RatingsValue;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    private Long idContentRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_content", referencedColumnName = "id_content", nullable = false)
    private Content idContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User idUser;

    @RatingsValue
    @NotNull
    @Column(name = "rating")
    private Double rating;
}
