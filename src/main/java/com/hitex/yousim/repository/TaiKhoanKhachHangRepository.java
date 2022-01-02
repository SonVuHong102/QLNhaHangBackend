package com.hitex.yousim.repository;

import com.hitex.yousim.model.TaiKhoanKhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaiKhoanKhachHangRepository extends JpaRepository<TaiKhoanKhachHang, Integer> {
    @Query("SELECT m FROM TaiKhoanKhachHang m WHERE m.id = ?1")
    TaiKhoanKhachHang getById(int id);
}