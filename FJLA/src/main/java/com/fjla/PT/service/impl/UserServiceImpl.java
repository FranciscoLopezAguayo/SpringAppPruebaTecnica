package com.fjla.PT.service.impl;

import com.fjla.PT.repository.IUserRepository;
import com.fjla.PT.model.Users;
import com.fjla.PT.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Users save(Users user) {
        return userRepository.save(user);
    }

}
