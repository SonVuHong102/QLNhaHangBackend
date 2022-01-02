package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class ThanhVien extends BaseEntity {
    private String hoten;
    @Temporal(TemporalType.DATE)
    private Date ngaysinh;
    private String sdt;
    private String diachi;
    private String email;
    private String ghichu;

}
