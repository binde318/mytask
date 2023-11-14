package com.netbinde.brilloconnectest.model;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class User {

    private String username;

    private String email;

    private String password;

    private LocalDate dob;


}
