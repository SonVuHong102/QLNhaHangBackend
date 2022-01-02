package com.hitex.yousim.model;

import lombok.Data;

@Data
public class NhanVien extends ThanhVien {
    private String chucvu;
    private int tblNhaHangId;
}
