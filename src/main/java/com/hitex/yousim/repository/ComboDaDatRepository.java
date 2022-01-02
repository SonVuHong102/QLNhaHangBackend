package com.hitex.yousim.repository;

import com.hitex.yousim.model.ComboDaDat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComboDaDatRepository extends JpaRepository<ComboDaDat, Integer> {
    @Query("SELECT m FROM ComboDaDat m WHERE m.tblBanDaDatId = ?1")
    List<ComboDaDat> getListComboDaDatTheoDatBan(int tblBanDaDatId);
    @Query("SELECT m FROM ComboDaDat m WHERE m.id = ?1")
    ComboDaDat getById(int id);
    @Modifying
    @Query("DELETE FROM ComboDaDat c WHERE c.tblBanDaDatId = ?1")
    void deleteComboDaDatByBanDaDat(int id);

}