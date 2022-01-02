package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tblBan")
@Data
public class Ban extends BaseEntity {
    private int soban;
    private float gia;
    private int songuoitoida;
    private String mota;
    private int tblNhaHangId;

}
