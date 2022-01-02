package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.thongke.ThongKeKhachHangReq;
import com.hitex.yousim.dto.response.thongke.ThongKeKhachHangRes;
import com.hitex.yousim.dto.response.thongke.ThongKeMonAnRes;
import com.hitex.yousim.service.ThongKeKhachHangService;
import com.hitex.yousim.service.ThongKeMonAnService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/api/")
public class ThongKeController extends BaseController {

    @Autowired
    ThongKeKhachHangService thongKeKhachHangService;
    @Autowired
    ThongKeMonAnService thongKeMonAnService;

    @PostMapping(value = "thongKeKhachHang")
    @ResponseBody
    public ResponseEntity thongKeKhachHang(@RequestBody BaseRequestData<ThongKeKhachHangReq> baseRequestData) {
        try {
            ThongKeKhachHangRes response = thongKeKhachHangService.thongKeKhachHang(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "thongKeChiTietKhachHang")
    @ResponseBody
    public ResponseEntity thongKeChiTietKhachHang(@RequestBody BaseRequestData<ThongKeKhachHangReq> baseRequestData) {
        try {
            ThongKeKhachHangRes response = thongKeKhachHangService.thongKeChiTietKhachHang(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "thongKeMonAn")
    @ResponseBody
    public ResponseEntity thongKeMonAn(@RequestBody BaseRequestData<ThongKeKhachHangReq> baseRequestData) {
        try {
            ThongKeMonAnRes response = thongKeMonAnService.thongKeMonAn(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}
