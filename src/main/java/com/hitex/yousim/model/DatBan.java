package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tblDatBan")
@Data
public class DatBan  extends BaseEntity{
    @Temporal(TemporalType.DATE)
    private Date ngaydat;
    private float tong;
    private String ghichu;
    private int tblTaiKhoanKhachHangId;
    private int tblBanDaDatId;
    @Transient
    private BanDaDat bandadat;
    @Transient
    private TaiKhoanKhachHang taikhoankhachhang;

}
