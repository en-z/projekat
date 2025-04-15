package org.projekat.service;

import org.projekat.dtos.LoginDTO;

public interface AuthServiceInterface {
    String login(LoginDTO loginDTO);
}
