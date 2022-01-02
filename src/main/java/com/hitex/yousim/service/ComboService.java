package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.combo.ComboRes;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface ComboService {
    ComboRes timCombo(BaseRequestData baseRequestData) throws ApplicationException;
    ComboRes chiTietCombo(BaseRequestData baseRequestData) throws ApplicationException;
    ComboRes danhSachCombo(BaseRequestData baseRequestData) throws ApplicationException;
    boolean themCombo(BaseRequestData baseRequestData) throws ApplicationException;
    boolean suaCombo(BaseRequestData baseRequestData) throws ApplicationException;
    boolean xoaCombo(BaseRequestData baseRequestData) throws ApplicationException;
}
