package com.shop.in.servicImpl;

import com.shop.in.inConstance.RoleConstance;
import com.shop.in.model.Person;
import com.shop.in.model.Profile;
import com.shop.in.model.Roles;
import com.shop.in.repository.PersonRepo;
import com.shop.in.repository.RolesRepo;
import com.shop.in.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private PersonRepo repo;

    @Autowired
    private RolesRepo rolesRepo;

    public Person addNewUser(Person person){

        Roles roles = rolesRepo.findByRoleName(RoleConstance.USER_ROLE);
        person.setRoles(roles);
        person.setPwd(encoder.encode(person.getPwd()));
        person.setCreatedAt(LocalDateTime.now());
        return repo.save(person);
    }

}
