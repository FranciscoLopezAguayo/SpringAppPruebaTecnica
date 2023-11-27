package com.fjla.PT.service.impl;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fjla.PT.model.Role;
import com.fjla.PT.model.Users;
import com.fjla.PT.repository.IUserRepository;



@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User not exists", username));
        }
        List<GrantedAuthority> roles = new ArrayList<>();
        ((Iterable<GrantedAuthority>) user.getRole()).forEach(rol -> {
            roles.add(new SimpleGrantedAuthority(((Role) rol).getRol()));
        });
        UserDetails ud = new User(user.getName(), user.getPassword(), user.isEnabled(), true, true, true, roles);
        return ud;
    }
}

/* 
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = (User) userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User not exists", username));
        }

        List<GrantedAuthority> roles = new ArrayList<>();

        //roles.add(user.getRoles());
        List<GrantedAuthority> roles = new ArrayList<>();
        user.getRoles().forEach(rol -> {
            roles.add(new SimpleGrantedAuthority(rol.getRol()));
        });
        UserDetails ud = new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, roles);
        return ud;
    }
}
*/