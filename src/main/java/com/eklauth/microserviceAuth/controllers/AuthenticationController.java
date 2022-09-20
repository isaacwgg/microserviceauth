package com.eklauth.microserviceAuth.controllers;

import com.eklauth.microserviceAuth.configurations.JwtTokenUtil;
import com.eklauth.microserviceAuth.entities.User;
import com.eklauth.microserviceAuth.requests.AuthRequest;
import com.eklauth.microserviceAuth.requests.RegisterUserRequest;
import com.eklauth.microserviceAuth.responses.UserView;
import com.eklauth.microserviceAuth.security.UserPrincipal;
import com.eklauth.microserviceAuth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 *
 * @author isaac
 */
@RestController
@RequestMapping(path="/authenticate")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    AuthService authService;
    @PostMapping("login")
    public ResponseEntity<UserView> login(@RequestBody @Valid AuthRequest request){
        Authentication authenticate= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassWord()));
        UserPrincipal principal = (UserPrincipal) authenticate.getPrincipal();
        User user =principal.getUser();
        UserView.Data data = new UserView.Data(); //instantiating static class =>class name then inner static class
        data.setAccessToken(jwtTokenUtil.generateAccessToken(user.getId(), user.getUsername()));
        data.setAuthorities(principal.getAuthorities().toArray());
        data.setFullName(user.getFullnames());
        data.setUserName(user.getUsername());
        
        UserView view = new UserView();
        
        view.setData(data);
        view.setMessage("SUCCESS");
        view.setSuccess(true);
        
     return ResponseEntity.ok(view);
    }
 @PostMapping("register")
 public ResponseEntity<?> saveUser(@RequestBody RegisterUserRequest request){
  return  ResponseEntity.ok(authService.registerUser(request));
 }

    
}
