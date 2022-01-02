package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.monandadat.MonAnDaDatRes;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface MonAnDaDatService {
    MonAnDaDatRes timMonAnDaDat(BaseRequestData baseRequestData) throws ApplicationException;
    MonAnDaDatRes chiTietMonAnDaDat(BaseRequestData baseRequestData) throws ApplicationException;
    boolean themMonAnDaDat(BaseRequestData baseRequestData) throws ApplicationException;
    boolean suaMonAnDaDat(BaseRequestData baseRequestData) throws ApplicationException;
    boolean xoaMonAnDaDat(BaseRequestData baseRequestData) throws ApplicationException;
}
