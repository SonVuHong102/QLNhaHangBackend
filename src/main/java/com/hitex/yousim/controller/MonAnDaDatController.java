package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.monandadat.MonAnDaDatReq;
import com.hitex.yousim.dto.response.monandadat.MonAnDaDatRes;
import com.hitex.yousim.service.MonAnDaDatService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/api/")
public class MonAnDaDatController extends BaseController {

    @Autowired
    MonAnDaDatService monAnDaDatService;

    @PostMapping(value = "themMonAnDaDat")
    @ResponseBody
    public ResponseEntity themMonAnDaDat(@RequestBody BaseRequestData<MonAnDaDatReq> baseRequestData) {
        try {
            boolean response = monAnDaDatService.themMonAnDaDat(baseRequestData);
            return success();
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "suaMonAnDaDat")
    @ResponseBody
    public ResponseEntity suaMonAnDaDat(@RequestBody BaseRequestData<MonAnDaDatReq> baseRequestData) {
        try {
            boolean response = monAnDaDatService.suaMonAnDaDat(baseRequestData);
            return success();
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "xoaMonAnDaDat")
    @ResponseBody
    public ResponseEntity xoaMonAnDaDat(@RequestBody BaseRequestData<MonAnDaDatReq> baseRequestData) {
        try {
            boolean response = monAnDaDatService.xoaMonAnDaDat(baseRequestData);
            return success();
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "timMonAnDaDat")
    @ResponseBody
    public ResponseEntity timMonAnDaDat(@RequestBody BaseRequestData<MonAnDaDatReq> baseRequestData) {
        try {
            MonAnDaDatRes response = monAnDaDatService.timMonAnDaDat(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "chiTietMonAnDaDat")
    @ResponseBody
    public ResponseEntity chiTietMonAnDaDat(@RequestBody BaseRequestData<MonAnDaDatReq> baseRequestData) {
        try {
            MonAnDaDatRes response = monAnDaDatService.chiTietMonAnDaDat(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}
