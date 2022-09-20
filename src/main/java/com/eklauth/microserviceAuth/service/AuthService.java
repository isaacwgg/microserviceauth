package com.eklauth.microserviceAuth.service;

import com.eklauth.microserviceAuth.entities.User;
import com.eklauth.microserviceAuth.repositories.UserRepo;
import com.eklauth.microserviceAuth.requests.RegisterUserRequest;
import com.eklauth.microserviceAuth.responses.BasicResponse;
import com.eklauth.microserviceAuth.utils.enums.RegisterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author isaac
 */
@Service
public class AuthService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public BasicResponse registerUser(RegisterUserRequest request){

        BasicResponse response = new BasicResponse();
        response.setMessage("Failed");
        response.setSuccess(false);
        try {
            User user = new User();
            user.setUsername(request.getUsername());
            user.setFullnames(request.getFullNames());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setIdNo(request.getIdNo());
            user.setEmail(request.getEmail());
            user.setGender(request.getGender());
            user.setPhone(request.getPhone());
            user.setStatus(true);
            user.setRegisterType(request.getRegisterType().equals("SELF") && !request.getRegisterType().isEmpty()? RegisterType.SELFREGISTER:RegisterType.PORTALREGISTERED);
            User savedUser =userRepo.save(user);
            response.setMessage("Success");
            response.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }
}
