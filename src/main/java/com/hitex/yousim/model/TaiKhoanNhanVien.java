package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tblTaiKhoanNhanVien")
@Data
public class TaiKhoanNhanVien extends BaseEntity {

    private String tendangnhap;
    private String matkhau;
    private int tblNhanVienId;
    @Transient
    private NhanVien nhanvien;

}
