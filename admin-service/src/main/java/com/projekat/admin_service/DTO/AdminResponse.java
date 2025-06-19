package com.projekat.admin_service.DTO;

import com.projekat.admin_service.entity.Adresa;

import java.util.List;

public class AdminResponse {

    public long id;
    public String email;
    public String password;
    public String ime;
    public String prezime;
    public List<String> roles;
    public String ulica;
    public String grad;
    public String drzava;
    public String broj;
}
