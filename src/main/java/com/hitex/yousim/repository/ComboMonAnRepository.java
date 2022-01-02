package com.hitex.yousim.repository;

import com.hitex.yousim.model.Combo_MonAn;
import com.hitex.yousim.model.MonAn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComboMonAnRepository extends JpaRepository<Combo_MonAn, Integer> {
    @Modifying
    @Query("DELETE FROM Combo_MonAn cma WHERE cma.tblComboId = ?1")
    void deleteCombo(int id);

    @Query("SELECT m FROM MonAn m, Combo_MonAn cma WHERE m.id = cma.tblMonAnId AND cma.tblComboId = ?1")
    List<MonAn> getListMonAnByCombo(int id);

    @Query("SELECT cma FROM Combo_MonAn cma WHERE cma.tblMonAnId = ?1")
    List<Combo_MonAn> getListMonAnbyMonAn(int id);
}