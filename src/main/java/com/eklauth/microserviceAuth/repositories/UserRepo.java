/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eklauth.microserviceAuth.repositories;

import com.eklauth.microserviceAuth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author isaac
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    User findByUsername(String username);

}
