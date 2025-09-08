package com.shop.in.service;

import com.shop.in.model.Contact;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactService {
    Contact saveInfo(Contact contact);

    List<Contact> getAllInfo();
}
