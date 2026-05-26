package com.zabisoft.alarm_app.dto;

import com.zabisoft.alarm_app.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PendingRegistration implements Serializable {

    private String name;

    private String email;

    private String password;

    private Role role;
}