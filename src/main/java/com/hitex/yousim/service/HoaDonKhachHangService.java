package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.hoadonkhachhang.HoaDonKhachHangRes;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface HoaDonKhachHangService {
    HoaDonKhachHangRes thanhToan(BaseRequestData baseRequestData) throws ApplicationException;
    HoaDonKhachHangRes danhSachHoaDonKhachHang(BaseRequestData baseRequestData) throws ApplicationException;
    HoaDonKhachHangRes chiTietHoaDonKhachHang(BaseRequestData baseRequestData) throws ApplicationException;
}
