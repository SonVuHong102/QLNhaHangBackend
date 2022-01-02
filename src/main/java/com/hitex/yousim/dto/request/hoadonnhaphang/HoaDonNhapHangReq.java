package com.hitex.yousim.dto.request.hoadonnhaphang;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.HoaDonNhapHang;
import com.hitex.yousim.model.PhieuNhap;
import lombok.Data;

import java.util.List;

@Data
public class HoaDonNhapHangReq extends HoaDonNhapHang implements IRequestData {
    private String textSearch;
    private int nhaCungCapId;
    private List<PhieuNhap> phieuNhapList;
    private String ghiChu;
    @Override
    public boolean isValid() {
        return false;
    }
}
