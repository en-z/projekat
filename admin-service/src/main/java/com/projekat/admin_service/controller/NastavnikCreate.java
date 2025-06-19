package com.projekat.admin_service.controller;

import com.projekat.admin_service.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin/nastavnik")
public class NastavnikCreate {
    @Autowired
    private AdminService adminService;
}
