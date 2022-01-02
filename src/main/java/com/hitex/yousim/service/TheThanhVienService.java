package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.thethanhvien.TheThanhVienRes;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface TheThanhVienService {
    TheThanhVienRes timTheThanhVien(BaseRequestData baseRequestData) throws ApplicationException;
    boolean themTheThanhVien(BaseRequestData baseRequestData) throws ApplicationException;
    boolean suaTheThanhVien(BaseRequestData baseRequestData) throws ApplicationException;
    boolean xoaTheThanhVien(BaseRequestData baseRequestData) throws ApplicationException;
}
