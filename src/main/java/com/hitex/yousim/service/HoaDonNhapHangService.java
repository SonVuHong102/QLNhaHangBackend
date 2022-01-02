package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.hoadonnhaphang.HoaDonNhapHangRes;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface HoaDonNhapHangService {
    HoaDonNhapHangRes nhapNguyenLieu(BaseRequestData baseRequestData) throws ApplicationException;
}
