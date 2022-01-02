package com.hitex.yousim.repository;

import com.hitex.yousim.model.Ban;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BanRepository extends JpaRepository<Ban, Integer> {
    @Query("SELECT b FROM Ban b")
    List<Ban> getListBan();
    @Query("SELECT m FROM Ban m WHERE m.id = ?1")
    Ban getById(int id);
}