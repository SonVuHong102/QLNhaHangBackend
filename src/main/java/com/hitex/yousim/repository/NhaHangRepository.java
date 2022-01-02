package com.hitex.yousim.repository;

import com.hitex.yousim.model.NhaHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NhaHangRepository extends JpaRepository<NhaHang, Integer> {
    @Query(value = "select n from NhaHang n where n.ten like %?1% or n.diachi like %?1%")
    List<NhaHang> getListNhaHang(String text);
    @Query("SELECT m FROM NhaHang m WHERE m.id = ?1")
    NhaHang getById(int id);
}
