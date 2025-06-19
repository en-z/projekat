package com.projekat.admin_service.service;

import com.projekat.admin_service.DTO.AdminDTO;
import com.projekat.admin_service.DTO.AdminResponse;
import com.projekat.admin_service.DTO.RegisterDTO;
import com.projekat.admin_service.client.AuthClient;
import com.projekat.admin_service.entity.Admin;
import com.projekat.admin_service.entity.Adresa;
import com.projekat.admin_service.repository.AdminRepository;
import com.projekat.admin_service.repository.AdresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AuthClient authClient;
    @Autowired
    private AdresaRepository adresaRepository;
    public Admin getById(long id){
       return adminRepository.findById(id).orElseThrow(()->new RuntimeException("error"));
    }
    public List<Admin> getAll(){
        return adminRepository.findAll();
    }
    public Admin create(AdminResponse dto){
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.email = dto.email;
        registerDTO.password =dto.password;
        registerDTO.ime = dto.ime;
        registerDTO.prezime= dto.prezime;
        RegisterDTO response = authClient.registruj(registerDTO).getBody();
        if(response == null){
            throw new RuntimeException("error nema odgovoroa od servica");
        }
        Adresa adresa = adresaRepository.findByUlicaAndBrojAndGradAndDrzava(dto.ulica,dto.broj,dto.grad,dto.drzava).orElse(null);
        if (adresa ==null){
            adresa = new Adresa();
            adresa.setBroj(dto.broj);
            adresa.setGrad(dto.grad);
            adresa.setDrzava(dto.drzava);
            adresa.setUlica(dto.ulica);
        }
        Admin admin = new Admin();
        admin.setAdresa(adresa);
        admin.setIme(dto.ime);
        admin.setUserId(response.id);
        admin.setPrezime(dto.prezime);
        return adminRepository.save(admin);
    }
    public Admin put(AdminDTO dto){
        Admin admin = adminRepository.findByUserId(dto.id).orElseThrow(()->new RuntimeException("nema id"));
        admin.setIme(dto.ime);
        admin.setPrezime(dto.prezime);
        Optional<Adresa> adresa = adresaRepository.findByUlicaAndBrojAndGradAndDrzava(dto.adresa.getUlica(),dto.adresa.getBroj(),dto.adresa.getGrad(),dto.adresa.getGrad());
        if(adresa.isPresent()){
            admin.setAdresa(adresa.get());
        }else{
            admin.setAdresa(dto.adresa);
        }
        return adminRepository.save(admin);
    }
    public void delete(long id){
        adminRepository.deleteByUserId(id);
        authClient.delete(id);
    }
}
