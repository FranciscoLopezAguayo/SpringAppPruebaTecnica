package com.fjla.PT.service;

import org.springframework.stereotype.Service;

import com.fjla.PT.model.Users;

@Service
public interface IUserService {
    public Users save(Users user);
}
