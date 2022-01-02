package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tblMonAn")
@Data
public class MonAn  extends BaseEntity {
    private String ten;
    private String mota;
    private float gia;
    private String img;
}
