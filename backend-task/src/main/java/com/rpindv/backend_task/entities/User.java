package com.rpindv.backend_task.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rpindv.backend_task.helpers.validators.custom_anotations.NotNullOrBlank;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @SequenceGenerator(name = "usersIdSeq", sequenceName = "usersIdSeq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usersIdSeq")
    @Column(name = "id_user", updatable = false, unique = true, nullable = false)
    private Integer id_user;

    @NotNullOrBlank(message = "Please provide your First Name")
    @Size(max = 40, message = "First Name length is too big, limit is 40")
    @Column(name = "firstname_user", nullable = false, length = 40)
    private String firstname_user;

    @NotNullOrBlank(message = "Please provide your Last Name")
    @Size(max = 40, message = "Last Name length is too big, limit is 40")
    @Column(name = "lastname_user", nullable = false, length = 40)
    private String lastname_user;

    @NotNullOrBlank(message = "Please provide a Nickname")
    @Size(max = 40, message = "Nickname length is too big, limit is 40")
    @Column(name = "nickname", nullable = false, length = 40)
    private String nickname;

    @NotNullOrBlank(message = "Please provide your Email")
    @Email(message = "Email should be valid")
    @Column(name = "email_user")
    private String email_user;

    @NotNullOrBlank
    @Column(name = "password_user")
    private String password_user;
}
