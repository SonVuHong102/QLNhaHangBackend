package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.combodadat.ComboDaDatRes;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface ComboDaDatService {
    ComboDaDatRes timComboDaDat(BaseRequestData baseRequestData) throws ApplicationException;
    ComboDaDatRes chiTietComboDaDat(BaseRequestData baseRequestData) throws ApplicationException;
    boolean themComboDaDat(BaseRequestData baseRequestData) throws ApplicationException;
    boolean suaComboDaDat(BaseRequestData baseRequestData) throws ApplicationException;
    boolean xoaComboDaDat(BaseRequestData baseRequestData) throws ApplicationException;
}
