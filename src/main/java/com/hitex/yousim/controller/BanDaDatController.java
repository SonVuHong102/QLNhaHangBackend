package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.combo.ComboReq;
import com.hitex.yousim.dto.response.bandadat.BanDaDatRes;
import com.hitex.yousim.service.BanDaDatService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/api/")
public class BanDaDatController extends BaseController {

    @Autowired
    BanDaDatService banDaDatService;

    @PostMapping(value = "danhSachBanTrong")
    @ResponseBody
    public ResponseEntity danhSachBanTrong(@RequestBody BaseRequestData<ComboReq> baseRequestData) {
        try {
            BanDaDatRes response = banDaDatService.danhSachBanTrong(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "danhSachBanDaDat")
    @ResponseBody
    public ResponseEntity danhSachBanDaDat(@RequestBody BaseRequestData<ComboReq> baseRequestData) {
        try {
            BanDaDatRes response = banDaDatService.danhSachBanDaDat(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }


}
