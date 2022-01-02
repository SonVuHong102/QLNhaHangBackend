package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tblHoaDonNhapHang")
@Data
public class HoaDonNhapHang  extends BaseEntity {
    private String ghichu;
    private int tblTaiKhoanNhanVienId;
    private int tblNhaCungCapId;
    @Transient
    private float tongtien;
    @Transient
    private NhaCungCap nhacungcap;
    @Transient
    private List<PhieuNhap> phieunhap;
    @Transient
    private TaiKhoanNhanVien taikhoannhanvien;

}
