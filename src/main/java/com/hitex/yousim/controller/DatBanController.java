package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.nguyenlieu.NguyenLieuReq;
import com.hitex.yousim.service.DatBanService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/api/")
public class DatBanController extends BaseController {
    @Autowired
    DatBanService datBanService;

    @PostMapping(value = "datBan")
    @ResponseBody
    public ResponseEntity datBan(@RequestBody BaseRequestData<NguyenLieuReq> baseRequestData) {
        try {
            boolean response = datBanService.datBan(baseRequestData);
            return success();
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "xoaDatBan")
    @ResponseBody
    public ResponseEntity xoaDatBan(@RequestBody BaseRequestData<NguyenLieuReq> baseRequestData) {
        try {
            boolean response = datBanService.xoaDatBan(baseRequestData);
            return success();
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}
