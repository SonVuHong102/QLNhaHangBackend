package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.thongke.ThongKeKhachHangRes;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface ThongKeKhachHangService {
    ThongKeKhachHangRes thongKeKhachHang(BaseRequestData baseRequestData) throws ApplicationException;
    ThongKeKhachHangRes thongKeChiTietKhachHang(BaseRequestData baseRequestData) throws ApplicationException;
}
