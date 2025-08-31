package com.shop.in.servicImpl;

import com.shop.in.BeanConfig.PropertiesConfiguration;
import com.shop.in.model.Contact;
import com.shop.in.repository.ContactRepo;
import com.shop.in.service.ContactService;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepo repo;

    @Autowired
    private PropertiesConfiguration properties;

    @Override
    public Contact saveInfo(Contact contact) {
        contact.setCreatedAt(LocalDateTime.now());
        contact.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return repo.save(contact);
    }

    @Override
    public List<Contact> getAllInfo() {
        return repo.findAll();
    }

    public void deleteContact(int id){
        repo.deleteById(id);
    }

    public Page<Contact> findPaginated(int pageNum ,String sortField ,String sortDir) {
        int pageSize = properties.getContactPageSize();

        Pageable pageable = PageRequest.of(pageNum-1,pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());

        Page<Contact> contactPage = repo.findAll(pageable);
        return contactPage;
    }
}
