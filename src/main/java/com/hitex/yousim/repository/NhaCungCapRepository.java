package com.hitex.yousim.repository;

import com.hitex.yousim.model.NhaCungCap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NhaCungCapRepository extends JpaRepository<NhaCungCap, Integer> {
    @Query("SELECT m FROM NhaCungCap m WHERE m.ten LIKE %?1%")
    List<NhaCungCap> getListNhaCungCap(String text);
    @Query("SELECT m FROM NhaCungCap m")
    List<NhaCungCap> getListNhaCungCap();
    @Query("SELECT m FROM NhaCungCap m WHERE m.id = ?1")
    NhaCungCap getById(int id);
}