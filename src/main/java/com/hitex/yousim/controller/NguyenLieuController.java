package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.nguyenlieu.NguyenLieuReq;
import com.hitex.yousim.dto.response.nguyenlieu.NguyenLieuRes;
import com.hitex.yousim.service.NguyenLieuService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/api/")
public class NguyenLieuController extends BaseController {

    @Autowired
    NguyenLieuService nguyenLieuService;

    @PostMapping(value = "themNguyenLieu")
    @ResponseBody
    public ResponseEntity themNguyenLieu(@RequestBody BaseRequestData<NguyenLieuReq> baseRequestData) {
        try {
            boolean response = nguyenLieuService.themNguyenLieu(baseRequestData);
            return success();
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "suaNguyenLieu")
    @ResponseBody
    public ResponseEntity suaNguyenLieu(@RequestBody BaseRequestData<NguyenLieuReq> baseRequestData) {
        try {
            boolean response = nguyenLieuService.suaNguyenLieu(baseRequestData);
            return success();
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "xoaNguyenLieu")
    @ResponseBody
    public ResponseEntity xoaNguyenLieu(@RequestBody BaseRequestData<NguyenLieuReq> baseRequestData) {
        try {
            boolean response = nguyenLieuService.xoaNguyenLieu(baseRequestData);
            return success();
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "timNguyenLieu")
    @ResponseBody
    public ResponseEntity timNguyenLieu(@RequestBody BaseRequestData<NguyenLieuReq> baseRequestData) {
        try {
            NguyenLieuRes response = nguyenLieuService.timNguyenLieu(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "danhSachNguyenLieu")
    @ResponseBody
    public ResponseEntity danhSachNguyenLieu(@RequestBody BaseRequestData<NguyenLieuReq> baseRequestData) {
        try {
            NguyenLieuRes response = nguyenLieuService.danhSachNguyenLieu(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "chiTietNguyenLieu")
    @ResponseBody
    public ResponseEntity chiTietNguyenLieu(@RequestBody BaseRequestData<NguyenLieuReq> baseRequestData) {
        try {
            NguyenLieuRes response = nguyenLieuService.chiTietNguyenLieu(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}
