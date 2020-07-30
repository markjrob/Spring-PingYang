package com.markjr.food.service;

import com.markjr.food.entity.Food;
import com.markjr.food.entity.MenuEN;
import com.markjr.food.entity.MenuTH;
import com.markjr.food.repository.FoodRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class FoodService {

    @Autowired
    private FoodRepository repository;

    private int total = 0;

    public List<Food> find() {
        return repository.findAll();
    }

    public Food findById(Long id)  throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Record"));
    }

    public Food saveTH(MenuTH menuTH) {
        String name = menuTH.getName();
        int cost = menuTH.getCost();
        String type = menuTH.getType();
        Food food = new Food();
        food.setName(name);
        food.setCost(cost);
        food.setType(type);
        total += cost;
        return repository.save(food);
    }

    public Food saveEN(MenuEN menuEN) {
        String name = menuEN.getName();
        int cost = menuEN.getCost();
        String type = menuEN.getType();
        Food food = new Food();
        food.setName(name);
        food.setCost(cost);
        food.setType(type);
        total += cost;
        return repository.save(food);
    }

    public void delete(Food food) {
        total -= food.getCost();
        repository.delete(food);
    }

    public void clear() {
        repository.deleteAll();
    }

    public int getTotal() {
        return total;
    }

    public void resetTotal() {
        total = 0;
    }
}
