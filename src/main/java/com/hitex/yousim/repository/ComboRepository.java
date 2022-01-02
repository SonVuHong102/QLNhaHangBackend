package com.hitex.yousim.repository;

import com.hitex.yousim.model.Combo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComboRepository extends JpaRepository<Combo, Integer> {
    @Query("SELECT m FROM Combo m WHERE m.ten LIKE %?1%")
    List<Combo> getListCombo(String text);
    @Query("SELECT m FROM Combo m")
    List<Combo> getListCombo();
    @Query("SELECT m FROM Combo m WHERE m.id = ?1")
    Combo getById(int id);
}