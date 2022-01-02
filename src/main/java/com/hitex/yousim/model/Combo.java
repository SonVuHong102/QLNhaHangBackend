package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tblCombo")
@Data
public class Combo extends BaseEntity {
    private String ten;
    private String mota;
    private float gia;

    @Transient
    private List<MonAn> monan;

    public Combo() {
    }
}
