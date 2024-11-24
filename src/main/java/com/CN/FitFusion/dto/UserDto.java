package com.CN.FitFusion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private String email;

    private String password;

    private int age;

    private String gender;

    private Long contactNo;
    
    private String userType;
}
