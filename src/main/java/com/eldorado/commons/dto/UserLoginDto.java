package com.eldorado.commons.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserLoginDto {

    @NonNull
    private String userName;
    @NonNull
    private String password;
}
