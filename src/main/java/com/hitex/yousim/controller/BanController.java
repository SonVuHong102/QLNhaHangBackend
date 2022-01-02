package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.combo.ComboReq;
import com.hitex.yousim.dto.response.ban.BanRes;
import com.hitex.yousim.service.BanService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/api/")
public class BanController extends BaseController {

    @Autowired
    BanService banService;

    @PostMapping(value = "danhSachBan")
    @ResponseBody
    public ResponseEntity danhSachBan(@RequestBody BaseRequestData<ComboReq> baseRequestData) {
        try {
            BanRes response = banService.danhSachBan(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}
