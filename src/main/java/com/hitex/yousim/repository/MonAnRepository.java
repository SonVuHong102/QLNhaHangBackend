package com.hitex.yousim.repository;

import com.hitex.yousim.model.MonAn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MonAnRepository extends JpaRepository<MonAn, Integer> {
    @Query("SELECT m FROM MonAn m WHERE m.ten LIKE %?1%")
    List<MonAn> getListMonAn(String text);
    @Query("SELECT m FROM MonAn m")
    List<MonAn> getListMonAn();
    @Query("SELECT m FROM MonAn m, Combo_MonAn cm WHERE cm.tblComboId = ?1 AND m.id = cm.tblMonAnId")
    List<MonAn> getListMonAnByComboId(int tblComboId);
    @Query("SELECT m FROM MonAn m WHERE m.id = ?1")
    MonAn getById(int id);
}