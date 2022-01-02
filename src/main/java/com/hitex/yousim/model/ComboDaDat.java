package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "tblComboDaDat")
@Data
public class ComboDaDat extends BaseEntity{
    private float gia;
    private int soluong;
    private float khuyenmai;
    private int tblBanDaDatId;
    private int tblComboId;
    @Transient
    private Combo combo;

    public ComboDaDat() {
    }
}
