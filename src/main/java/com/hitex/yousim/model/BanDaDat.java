package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tblBanDaDat")
@Data
public class BanDaDat  extends BaseEntity{
    private float gia;
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngaynhanban;
    private float khuyenmai;
    private int tblBanId;

    @Transient
    private Ban ban;
    @Transient
    private List<ComboDaDat> combodadat;
    @Transient
    private List<MonAnDaDat> monandadat;

}
