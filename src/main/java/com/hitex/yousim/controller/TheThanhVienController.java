package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.thethanhvien.TheThanhVienReq;
import com.hitex.yousim.dto.response.thethanhvien.TheThanhVienRes;
import com.hitex.yousim.service.TheThanhVienService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/api/")
public class TheThanhVienController extends BaseController {

    @Autowired
    TheThanhVienService theThanhVienService;

    @PostMapping(value = "themTheThanhVien")
    @ResponseBody
    public ResponseEntity themTheThanhVien(@RequestBody BaseRequestData<TheThanhVienReq> baseRequestData) {
        try {
            boolean response = theThanhVienService.themTheThanhVien(baseRequestData);
            return success();
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "suaTheThanhVien")
    @ResponseBody
    public ResponseEntity suaTheThanhVien(@RequestBody BaseRequestData<TheThanhVienReq> baseRequestData) {
        try {
            boolean response = theThanhVienService.suaTheThanhVien(baseRequestData);
            return success();
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "xoaTheThanhVien")
    @ResponseBody
    public ResponseEntity xoaTheThanhVien(@RequestBody BaseRequestData<TheThanhVienReq> baseRequestData) {
        try {
            boolean response = theThanhVienService.xoaTheThanhVien(baseRequestData);
            return success();
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "timTheThanhVien")
    @ResponseBody
    public ResponseEntity timTheThanhVien(@RequestBody BaseRequestData<TheThanhVienReq> baseRequestData) {
        try {
            TheThanhVienRes response = theThanhVienService.timTheThanhVien(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}
