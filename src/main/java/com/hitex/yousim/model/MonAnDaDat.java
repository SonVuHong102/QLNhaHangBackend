package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tblMonAnDaDat")
@Data
public class MonAnDaDat  extends BaseEntity {
    private float gia;
    private int soluong;
    private float khuyenmai;
    private int tblBanDaDatId;
    private int tblMonAnId;
    @Transient
    private MonAn monan;

}
