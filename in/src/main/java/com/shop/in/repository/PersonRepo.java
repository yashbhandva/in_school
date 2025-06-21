package com.shop.in.repository;

import com.shop.in.model.EazyClass;
import com.shop.in.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepo extends JpaRepository<Person,Integer> {
    Person findUserByEmail(String email);

    Person readByEmail(Authentication authentication);

    List<Person> findByEazyClass(EazyClass eazyClass);

}
