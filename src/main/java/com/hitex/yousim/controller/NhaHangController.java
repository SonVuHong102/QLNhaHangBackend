package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.nhaHang.NhaHangReq;
import com.hitex.yousim.dto.response.nhahang.NhaHangRes;
import com.hitex.yousim.service.NhaHangService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/api/")
public class NhaHangController extends BaseController{
    @Autowired
    NhaHangService nhaHangService;

    @PostMapping(value = "themNhaHang")
    @ResponseBody
    public ResponseEntity themNhaHang(@RequestBody BaseRequestData<NhaHangReq> requestData) throws ApplicationException {
        try {
            boolean response = nhaHangService.themNhaHang(requestData);
            return success();
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "suaNhaHang")
    @ResponseBody
    public ResponseEntity suaNhaHang(@RequestBody BaseRequestData<NhaHangReq> requestData) throws ApplicationException {
        try {
            boolean response = nhaHangService.suaNhaHang(requestData);
            return success();
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "xoaNhaHang")
    @ResponseBody
    public ResponseEntity xoaNhaHang(@RequestBody BaseRequestData<NhaHangReq> requestData) throws ApplicationException {
        try {
            boolean response = nhaHangService.xoaNhaHang(requestData);
            return success();
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "chiTietNhaHang")
    @ResponseBody
    public ResponseEntity chiTietNhaHang(@RequestBody BaseRequestData<NhaHangReq> requestData) throws ApplicationException {
        try {
            NhaHangRes response = nhaHangService.chiTietNhaHang(requestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "danhSachNhaHang")
    @ResponseBody
    public ResponseEntity danhSachNhaHang(@RequestBody BaseRequestData<NhaHangReq> requestData) throws ApplicationException {
        try {
            NhaHangRes response = nhaHangService.danhSachNhaHang(requestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}
