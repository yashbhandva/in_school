package com.shop.in.servicImpl;

import com.shop.in.model.Person;
import com.shop.in.model.UserPrincipal;
import com.shop.in.repository.PersonRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonRepo repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Person person = repo.findUserByEmail(email);
        if (person == null){
            System.out.println("user not found!");
            log.info("user not found!");
            throw new UsernameNotFoundException("user not found!");
        }
        return new UserPrincipal(person);
    }
}
