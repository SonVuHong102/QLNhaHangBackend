package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.nhacungcap.NhaCungCapReq;
import com.hitex.yousim.dto.response.nhacungcap.NhaCungCapRes;
import com.hitex.yousim.service.NhaCungCapService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/api/")
public class NhaCungCapController extends BaseController {

    @Autowired
    NhaCungCapService nhaCungCapService;

    @PostMapping(value = "themNhaCungCap")
    @ResponseBody
    public ResponseEntity themNhaCungCap(@RequestBody BaseRequestData<NhaCungCapReq> baseRequestData) {
        try {
            boolean response = nhaCungCapService.themNhaCungCap(baseRequestData);
            return success();
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "suaNhaCungCap")
    @ResponseBody
    public ResponseEntity suaNhaCungCap(@RequestBody BaseRequestData<NhaCungCapReq> baseRequestData) {
        try {
            boolean response = nhaCungCapService.suaNhaCungCap(baseRequestData);
            return success();
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "xoaNhaCungCap")
    @ResponseBody
    public ResponseEntity xoaNhaCungCap(@RequestBody BaseRequestData<NhaCungCapReq> baseRequestData) {
        try {
            boolean response = nhaCungCapService.xoaNhaCungCap(baseRequestData);
            return success();
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "timNhaCungCap")
    @ResponseBody
    public ResponseEntity timNhaCungCap(@RequestBody BaseRequestData<NhaCungCapReq> baseRequestData) {
        try {
            NhaCungCapRes response = nhaCungCapService.timNhaCungCap(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "danhSachNhaCungCap")
    @ResponseBody
    public ResponseEntity danhSachNhaCungCap(@RequestBody BaseRequestData<NhaCungCapReq> baseRequestData) {
        try {
            NhaCungCapRes response = nhaCungCapService.danhSachNhaCungCap(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "chiTietNhaCungCap")
    @ResponseBody
    public ResponseEntity chiTietNhaCungCap(@RequestBody BaseRequestData<NhaCungCapReq> baseRequestData) {
        try {
            NhaCungCapRes response = nhaCungCapService.chiTietNhaCungCap(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}
