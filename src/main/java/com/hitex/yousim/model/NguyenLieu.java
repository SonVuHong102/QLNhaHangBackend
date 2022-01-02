package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "tblNguyenLieu")
@Data
public class NguyenLieu  extends BaseEntity{
    private String ten;
    private float dongia;
    private String loai;
    @Temporal(TemporalType.DATE)
    private Date hansudung;
    private String mota;

}
