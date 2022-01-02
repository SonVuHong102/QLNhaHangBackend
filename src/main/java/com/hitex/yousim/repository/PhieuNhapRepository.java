package com.hitex.yousim.repository;

import com.hitex.yousim.model.PhieuNhap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PhieuNhapRepository extends JpaRepository<PhieuNhap, Integer> {
    @Query("SELECT m FROM PhieuNhap m WHERE m.id = ?1")
    PhieuNhap getById(int id);
}