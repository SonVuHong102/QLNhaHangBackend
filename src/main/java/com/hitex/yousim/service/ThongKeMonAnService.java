package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.thongke.ThongKeMonAnRes;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface ThongKeMonAnService {
    ThongKeMonAnRes thongKeMonAn(BaseRequestData baseRequestData) throws ApplicationException;
}
