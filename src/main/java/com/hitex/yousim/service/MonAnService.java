package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.monan.MonAnRes;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface MonAnService {
    MonAnRes timMonAn(BaseRequestData baseRequestData) throws ApplicationException;
    MonAnRes chiTietMonAn(BaseRequestData baseRequestData) throws ApplicationException;
    MonAnRes danhSachMonAn(BaseRequestData baseRequestData) throws ApplicationException;
    boolean themMonAn(BaseRequestData baseRequestData) throws ApplicationException;
    boolean suaMonAn(BaseRequestData baseRequestData) throws ApplicationException;
    boolean xoaMonAn(BaseRequestData baseRequestData) throws ApplicationException;
}
