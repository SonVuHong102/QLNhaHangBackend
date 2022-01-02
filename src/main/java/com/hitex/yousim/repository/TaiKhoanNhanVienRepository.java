package com.hitex.yousim.repository;

import com.hitex.yousim.model.TaiKhoanNhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaiKhoanNhanVienRepository extends JpaRepository<TaiKhoanNhanVien, Integer> {
    @Query("SELECT m FROM TaiKhoanNhanVien m WHERE m.id = ?1")
    TaiKhoanNhanVien getById(int id);
    @Query("SELECT m FROM TaiKhoanNhanVien m WHERE m.tblNhanVienId = ?1")
    TaiKhoanNhanVien getByNhanVienId(int id);
}