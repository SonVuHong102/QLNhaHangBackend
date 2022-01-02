package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tblCombo_MonAn")
@Data
public class Combo_MonAn extends BaseEntity {
    private int tblMonAnId;
    private int tblComboId;

}
