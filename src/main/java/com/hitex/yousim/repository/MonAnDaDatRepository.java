package com.hitex.yousim.repository;

import com.hitex.yousim.model.MonAnDaDat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MonAnDaDatRepository extends JpaRepository<MonAnDaDat, Integer> {
    @Query("SELECT m FROM MonAnDaDat m WHERE m.tblBanDaDatId = ?1")
    List<MonAnDaDat> getListMonAnDaDat(int id);
    @Query("SELECT m FROM MonAnDaDat m WHERE m.id = ?1")
    MonAnDaDat getById(int id);
    @Modifying
    @Query("DELETE FROM MonAnDaDat c WHERE c.tblBanDaDatId = ?1")
    void deleteMonAnDaDatByBanDaDat(int id);
}