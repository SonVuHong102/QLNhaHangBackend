package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.hoadonnhaphang.HoaDonNhapHangReq;
import com.hitex.yousim.dto.response.hoadonnhaphang.HoaDonNhapHangRes;
import com.hitex.yousim.service.HoaDonNhapHangService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/api/")
public class HoaDonNhapHangController extends BaseController {

    @Autowired
    HoaDonNhapHangService hoaDonNhapHangService;

    @PostMapping(value = "nhapNguyenLieu")
    @ResponseBody
    public ResponseEntity nhapNguyenLieu(@RequestBody BaseRequestData<HoaDonNhapHangReq> baseRequestData) {
        try {
            HoaDonNhapHangRes response = hoaDonNhapHangService.nhapNguyenLieu(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

}
