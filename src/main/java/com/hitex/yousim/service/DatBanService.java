package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.datban.DatBanRes;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface DatBanService {
    DatBanRes danhSachDatBan(BaseRequestData baseRequestData) throws ApplicationException;
    DatBanRes chiTietDatBan(BaseRequestData baseRequestData) throws ApplicationException;
    boolean datBan(BaseRequestData baseRequestData) throws ApplicationException;
//    boolean suaDatBan(BaseRequestData baseRequestData) throws ApplicationException;
    boolean xoaDatBan(BaseRequestData baseRequestData) throws ApplicationException;
}
