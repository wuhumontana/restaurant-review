package com.ex.base.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ex.base.entity.Registrar;
import com.ex.base.jpa.RegistrarRepository;

@Service("registrarService")
public class RegistrarServiceImpl implements RegistrarService {
    
    @Autowired
    RegistrarRepository registrarRepository;
    
    @Override
    public Registrar findById(Long id) {
        
        System.out.println("********************    " + "in RegistrarServiceImpl, findByID " + "id= " + id);
        Optional<Registrar> registrar = registrarRepository.findById(id);
        
        System.out.println("%%%%%%%%%%%%%  " + "registrar  = " + registrar.toString());
        
        return registrar.orElse(null);
    }
}
