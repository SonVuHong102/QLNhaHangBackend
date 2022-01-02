package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.bandadat.BanDaDatRes;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface BanDaDatService {
    BanDaDatRes danhSachBanTrong(BaseRequestData baseRequestData) throws ApplicationException;
    BanDaDatRes danhSachBanDaDat(BaseRequestData baseRequestData) throws ApplicationException;
}
