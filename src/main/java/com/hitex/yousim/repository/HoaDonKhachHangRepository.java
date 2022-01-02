package com.hitex.yousim.repository;

import com.hitex.yousim.model.HoaDonKhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface HoaDonKhachHangRepository extends JpaRepository<HoaDonKhachHang, Integer> {
    @Query("SELECT k FROM HoaDonKhachHang k WHERE k.ngaythanhtoan <= ?1 AND k.ngaythanhtoan >= ?2")
    List<HoaDonKhachHang> getListHoaDonKhachHang(Date startDate, Date endDate);
    @Query("SELECT hdkh " +
            "FROM HoaDonKhachHang hdkh,DatBan db,TaiKhoanKhachHang tkkh,User u\n" +
            "WHERE hdkh.ngaythanhtoan <= ?2\n" +
            "AND hdkh.ngaythanhtoan >= ?3\n" +
            "AND db.id = hdkh.tblDatBanId\n" +
            "AND db.tblTaiKhoanKhachHangId = tkkh.id\n" +
            "AND tkkh.id = u.id\n" +
            "AND u.id = ?1")
    List<HoaDonKhachHang> getListHoaDonKhachHang(int khachHangId,Date startDate, Date endDate);
    @Query("SELECT m FROM HoaDonKhachHang m WHERE m.id = ?1")
    HoaDonKhachHang getById(int id);
}