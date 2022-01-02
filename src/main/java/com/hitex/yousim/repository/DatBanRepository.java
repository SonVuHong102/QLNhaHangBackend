package com.hitex.yousim.repository;

import com.hitex.yousim.model.DatBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface DatBanRepository extends JpaRepository<DatBan, Integer> {
    @Query("SELECT db FROM DatBan db,BanDaDat bdd WHERE db.tblBanDaDatId = bdd.id AND bdd.ngaynhanban >= ?1 AND bdd.ngaynhanban <= ?2")
    List<DatBan> getListDatBan(Date startDate, Date endDate);
    @Query("SELECT m FROM DatBan m WHERE m.id = ?1")
    DatBan getById(int id);
}