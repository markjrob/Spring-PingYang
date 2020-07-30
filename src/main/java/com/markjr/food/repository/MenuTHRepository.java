package com.markjr.food.repository;

import com.markjr.food.entity.MenuTH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuTHRepository extends JpaRepository<MenuTH, Long> {

    List<MenuTH> findByType(String type);
}
