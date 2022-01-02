package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.monan.MonAnReq;
import com.hitex.yousim.dto.response.monan.MonAnRes;
import com.hitex.yousim.service.MonAnService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/api/")
public class MonAnController extends BaseController {

    @Autowired
    MonAnService monAnService;

    @PostMapping(value = "themMonAn")
    @ResponseBody
    public ResponseEntity themMonAn(@RequestBody BaseRequestData<MonAnReq> baseRequestData) {
        try {
            boolean response = monAnService.themMonAn(baseRequestData);
            return success();
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "suaMonAn")
    @ResponseBody
    public ResponseEntity suaMonAn(@RequestBody BaseRequestData<MonAnReq> baseRequestData) {
        try {
            boolean response = monAnService.suaMonAn(baseRequestData);
            return success();
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "xoaMonAn")
    @ResponseBody
    public ResponseEntity xoaMonAn(@RequestBody BaseRequestData<MonAnReq> baseRequestData) {
        try {
            boolean response = monAnService.xoaMonAn(baseRequestData);
            return success();
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "timMonAn")
    @ResponseBody
    public ResponseEntity timMonAn(@RequestBody BaseRequestData<MonAnReq> baseRequestData) {
        try {
            MonAnRes response = monAnService.timMonAn(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "danhSachMonAn")
    @ResponseBody
    public ResponseEntity danhSachMonAn(@RequestBody BaseRequestData<MonAnReq> baseRequestData) {
        try {
            MonAnRes response = monAnService.danhSachMonAn(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "chiTietMonAn")
    @ResponseBody
    public ResponseEntity chiTietMonAn(@RequestBody BaseRequestData<MonAnReq> baseRequestData) {
        try {
            MonAnRes response = monAnService.chiTietMonAn(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}
