package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.combo.ComboReq;
import com.hitex.yousim.dto.response.combo.ComboRes;
import com.hitex.yousim.service.ComboService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/api/")
public class ComboController extends BaseController {

    @Autowired
    ComboService comboService;

    @PostMapping(value = "themCombo")
    @ResponseBody
    public ResponseEntity themCombo(@RequestBody BaseRequestData<ComboReq> baseRequestData) {
        try {
            boolean response = comboService.themCombo(baseRequestData);
            return success();
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "suaCombo")
    @ResponseBody
    public ResponseEntity suaCombo(@RequestBody BaseRequestData<ComboReq> baseRequestData) {
        try {
            boolean response = comboService.suaCombo(baseRequestData);
            return success();
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "xoaCombo")
    @ResponseBody
    public ResponseEntity xoaCombo(@RequestBody BaseRequestData<ComboReq> baseRequestData) {
        try {
            boolean response = comboService.xoaCombo(baseRequestData);
            return success();
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "timCombo")
    @ResponseBody
    public ResponseEntity timCombo(@RequestBody BaseRequestData<ComboReq> baseRequestData) {
        try {
            ComboRes response = comboService.timCombo(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "danhSachCombo")
    @ResponseBody
    public ResponseEntity danhSachCombo(@RequestBody BaseRequestData<ComboReq> baseRequestData) {
        try {
            ComboRes response = comboService.danhSachCombo(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "chiTietCombo")
    @ResponseBody
    public ResponseEntity chiTietCombo(@RequestBody BaseRequestData<ComboReq> baseRequestData) {
        try {
            ComboRes response = comboService.chiTietCombo(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}
