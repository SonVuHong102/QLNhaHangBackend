package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tblNhaCungCap")
@Data
public class NhaCungCap  extends BaseEntity {
    private String ten;
    private String diachi;
    private String ghichu;
}
