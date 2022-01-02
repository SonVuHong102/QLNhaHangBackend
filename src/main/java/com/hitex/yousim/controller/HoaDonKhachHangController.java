package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.hoadonkhachhang.HoaDonKhachHangReq;
import com.hitex.yousim.dto.response.hoadonkhachhang.HoaDonKhachHangRes;
import com.hitex.yousim.service.HoaDonKhachHangService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/api/")
public class HoaDonKhachHangController extends BaseController {

    @Autowired
    HoaDonKhachHangService hoaDonKhachHangService;

    @PostMapping(value = "thanhToan")
    @ResponseBody
    public ResponseEntity thanhToan(@RequestBody BaseRequestData<HoaDonKhachHangReq> baseRequestData) {
        try {
            HoaDonKhachHangRes response = hoaDonKhachHangService.thanhToan(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

}
