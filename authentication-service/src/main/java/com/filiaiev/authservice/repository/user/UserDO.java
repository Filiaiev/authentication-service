package com.filiaiev.authservice.repository.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

@Entity(name = "users")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NaturalId
    private String email;
    private String password;
    @Convert(converter = UserRoleDOConverter.class)
    private UserRoleDO role;
}
