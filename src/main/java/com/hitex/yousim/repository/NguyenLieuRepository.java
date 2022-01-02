package com.hitex.yousim.repository;

import com.hitex.yousim.model.NguyenLieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NguyenLieuRepository extends JpaRepository<NguyenLieu, Integer> {
    @Query("SELECT m FROM NguyenLieu m WHERE m.ten LIKE %?1%")
    List<NguyenLieu> getListNguyenLieu(String text);
    @Query("SELECT m FROM NguyenLieu m")
    List<NguyenLieu> getListNguyenLieu();
    @Query("SELECT m FROM NguyenLieu m WHERE m.id = ?1")
    NguyenLieu getById(int id);
}
