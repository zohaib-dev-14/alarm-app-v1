package com.zabisoft.alarm_app.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zabisoft.alarm_app.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    private String name;
    @Column(unique = true, nullable = false)
    private String email;


    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private boolean isVerified = false;
    private boolean isActive = true;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
