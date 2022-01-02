package com.hitex.yousim.repository;

import com.hitex.yousim.model.TheThanhVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TheThanhVienRepository extends JpaRepository<TheThanhVien, Integer> {
    @Query("SELECT m FROM TheThanhVien m WHERE m.id = ?1")
    TheThanhVien getById(int id);
    @Query("SELECT ttv FROM TheThanhVien ttv, User u WHERE ttv.tblKhachHangId = u.id AND u.name LIKE %?1%")
    List<TheThanhVien> getListTheThanhVien(String textSearch);
}