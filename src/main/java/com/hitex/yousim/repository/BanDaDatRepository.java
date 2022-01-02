package com.hitex.yousim.repository;

import com.hitex.yousim.model.Ban;
import com.hitex.yousim.model.BanDaDat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface BanDaDatRepository extends JpaRepository<BanDaDat, Integer> {
    @Query("SELECT b FROM Ban b, BanDaDat bdd WHERE b.id <> bdd.tblBanId AND bdd.ngaynhanban = ?1")
    List<Ban> getListBanTrong(Date date);
    @Query("SELECT b FROM Ban b, BanDaDat bdd WHERE b.id = bdd.tblBanId AND bdd.ngaynhanban = ?1")
    List<Ban> getListBanDaDat(Date date);
    @Query("SELECT bdd FROM BanDaDat bdd WHERE bdd.tblBanId = ?1 AND bdd.ngaynhanban = ?2")
    List<BanDaDat> checkBanDaDat(int banId,Date date);
    @Query("SELECT m FROM BanDaDat m WHERE m.id = ?1")
    BanDaDat getById(int id);
}