package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.nhacungcap.NhaCungCapRes;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface NhaCungCapService {
    NhaCungCapRes timNhaCungCap(BaseRequestData baseRequestData) throws ApplicationException;
    NhaCungCapRes chiTietNhaCungCap(BaseRequestData baseRequestData) throws ApplicationException;
    NhaCungCapRes danhSachNhaCungCap(BaseRequestData baseRequestData) throws ApplicationException;
    boolean themNhaCungCap(BaseRequestData baseRequestData) throws ApplicationException;
    boolean suaNhaCungCap(BaseRequestData baseRequestData) throws ApplicationException;
    boolean xoaNhaCungCap(BaseRequestData baseRequestData) throws ApplicationException;
}
