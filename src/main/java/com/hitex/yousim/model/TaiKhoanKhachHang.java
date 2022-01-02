package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tblTaiKhoanKhachHang")
@Data
public class TaiKhoanKhachHang extends BaseEntity {
    private int diemkhuyenmai;
    private int tblTheThanhVienId;
    private int tblKhachHangId;
    @Transient
    private TheThanhVien thethanhvien;
    @Transient
    private KhachHang khachhang;

}
