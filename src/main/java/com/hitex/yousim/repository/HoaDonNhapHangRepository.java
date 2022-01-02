package com.hitex.yousim.repository;

import com.hitex.yousim.model.HoaDonNhapHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HoaDonNhapHangRepository extends JpaRepository<HoaDonNhapHang, Integer> {
    @Query("SELECT m FROM HoaDonNhapHang m WHERE m.id = ?1")
    HoaDonNhapHang getById(int id);
}