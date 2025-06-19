package com.projekat.admin_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterDTO {
    public long id;
    public String email;
    public String password;
    public String ime;
    public String prezime;
}

