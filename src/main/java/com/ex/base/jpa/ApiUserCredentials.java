package com.ex.base.jpa;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApiUserCredentials {
    private String email, password;
}
