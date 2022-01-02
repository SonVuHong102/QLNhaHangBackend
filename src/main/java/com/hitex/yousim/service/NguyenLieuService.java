package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.nguyenlieu.NguyenLieuRes;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface NguyenLieuService {
    NguyenLieuRes timNguyenLieu(BaseRequestData baseRequestData) throws ApplicationException;
    NguyenLieuRes chiTietNguyenLieu(BaseRequestData baseRequestData) throws ApplicationException;
    NguyenLieuRes danhSachNguyenLieu(BaseRequestData baseRequestData) throws ApplicationException;
    boolean themNguyenLieu(BaseRequestData baseRequestData) throws ApplicationException;
    boolean suaNguyenLieu(BaseRequestData baseRequestData) throws ApplicationException;
    boolean xoaNguyenLieu(BaseRequestData baseRequestData) throws ApplicationException;
}
