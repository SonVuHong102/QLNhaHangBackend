package com.hitex.yousim.dto.request.hoadonkhachhang;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.HoaDonKhachHang;
import lombok.Data;

@Data
public class HoaDonKhachHangReq extends HoaDonKhachHang implements IRequestData {
    private String textSearch;
    private int datBanId;
    @Override
    public boolean isValid() {
        return false;
    }
}
