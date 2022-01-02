package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tblNhaHang")
@Data
public class NhaHang  extends BaseEntity {
    private String ten;
    private String chinhanh;
    private String diachi;

    @Transient
    private List<Ban> ban;
    @Transient
    private List<NhanVien> nhanvien;

}
