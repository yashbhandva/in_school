package com.shop.in.servicImpl;

import com.shop.in.model.EazyClass;
import com.shop.in.repository.EazyClassRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EazyClassServiceImpl {

    @Autowired
    private EazyClassRepo repo;

    public EazyClass saveClass(EazyClass eazyClass){
        return repo.save(eazyClass);
    }

    public List<EazyClass> findAllClasses(){
        return repo.findAll();
    }

    public boolean deleteClass(int id){
        Optional<EazyClass> isExist = repo.findById(id);
        if (isExist.isPresent()){
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
