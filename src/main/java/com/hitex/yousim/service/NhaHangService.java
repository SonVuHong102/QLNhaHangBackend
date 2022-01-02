package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.nhahang.NhaHangRes;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface NhaHangService {
    boolean themNhaHang(BaseRequestData baseRequestData) throws ApplicationException;
    boolean suaNhaHang(BaseRequestData baseRequestData) throws ApplicationException;
    boolean xoaNhaHang(BaseRequestData baseRequestData) throws ApplicationException;
    NhaHangRes chiTietNhaHang(BaseRequestData baseRequestData) throws ApplicationException;
    NhaHangRes danhSachNhaHang(BaseRequestData baseRequestData) throws ApplicationException;
}
