package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.ban.BanRes;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface BanService {
    BanRes danhSachBan(BaseRequestData baseRequestData) throws ApplicationException;
}
