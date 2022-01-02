package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tblHoaDonKhachHang")
@Data
public class HoaDonKhachHang  extends BaseEntity {
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngaythanhtoan;
    private String kieuthanhtoan;
    private String ghichu;
    private int tblDatBanId;
    private int tblTaiKhoanNhanVienId;
    @Transient
    private float tongtien;
    @Transient
    private DatBan datban;
    @Transient
    private TaiKhoanNhanVien taikhoannhanvien;

}
