package com.eklauth.microserviceAuth.security;

import com.eklauth.microserviceAuth.entities.User;
import com.eklauth.microserviceAuth.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetails implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =userRepo.findByUsername(username);
        if(user!=null){
            return UserPrincipal.create(user);
        }else{
            throw new UsernameNotFoundException("User not found");
        }
    }

    public UserDetails loadUserById(Integer id){
        User user =userRepo.findById(id).orElse(null);
        if(user!=null){
            return UserPrincipal.create(user);
        }else{
            throw new UsernameNotFoundException("User not found");
        }
    }
}
