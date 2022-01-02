package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tblTheThanhVien")
@Data
public class TheThanhVien  extends BaseEntity {
    private int diem;
    private String loai;
    private int tblKhachHangId;
}

