package com.hitex.yousim.dto.response.hoadonkhachhang;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.HoaDonKhachHang;
import lombok.Data;

import java.util.List;

@Data
public class HoaDonKhachHangRes implements IResponseData {
    List<HoaDonKhachHang> hoaDonKhachHangList;
}
