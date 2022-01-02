package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tblPhieuNhap")
@Data
public class PhieuNhap extends BaseEntity{
    @Temporal(TemporalType.DATE)
    private Date ngaynhap;
    private int soluong;
    private float dongia;
    private int tblNguyenLieuId;
    private int tblHoaDonNhapHangId;
    @Transient
    private NguyenLieu nguyenlieu;

}
