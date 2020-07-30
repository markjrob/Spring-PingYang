package com.markjr.food.service;

import com.markjr.food.entity.MenuEN;
import com.markjr.food.entity.MenuTH;
import com.markjr.food.repository.MenuENRepository;
import com.markjr.food.repository.MenuTHRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuTHRepository repositoryTH;
    @Autowired
    private MenuENRepository repositoryEN;

    public List<MenuTH> findTH(String type) {
        return repositoryTH.findByType(type);
    }

    public List<MenuEN> findEN(String type) {
        return repositoryEN.findByType(type);
    }

    public MenuTH findByIdTH(Long id) throws NotFoundException {
        return repositoryTH.findById(id).orElseThrow(() -> new NotFoundException("Not Found Record"));
    }

    public MenuEN findByIdEN(Long id) throws NotFoundException {
        return repositoryEN.findById(id).orElseThrow(() -> new NotFoundException("Not Found Record"));
    }
}
