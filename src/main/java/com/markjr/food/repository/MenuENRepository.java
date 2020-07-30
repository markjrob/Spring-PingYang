package com.markjr.food.repository;

import com.markjr.food.entity.MenuEN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuENRepository extends JpaRepository<MenuEN, Long> {

    List<MenuEN> findByType(String type);
}
