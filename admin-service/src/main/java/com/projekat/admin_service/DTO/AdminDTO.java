package com.projekat.admin_service.DTO;

import com.projekat.admin_service.entity.Adresa;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {
   public long id;
   public String email;
   public String password;
   public String ime;
   public String prezime;
   public List<String> roles;
   public Adresa adresa;

}
