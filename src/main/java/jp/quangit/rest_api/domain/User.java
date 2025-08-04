package jp.quangit.rest_api.domain;

import java.time.Instant;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jp.quangit.rest_api.utils.constant.GenderEnum;
import lombok.Getter;
import lombok.Setter;

//can use lambook to generate getter setter like data class in kotlin

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @NotBlank(message = "email khong duoc de trong ")
    private String email;
    private String password;
    private int age;
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
    private String address;
    private String refreshToken;
    private Instant createdAt;
    private Instant updatedAt;
    private String createdBy;
    private String updatedBy;

}
