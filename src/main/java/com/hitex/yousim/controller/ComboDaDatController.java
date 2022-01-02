package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.combadadat.ComboDaDatReq;
import com.hitex.yousim.dto.response.combodadat.ComboDaDatRes;
import com.hitex.yousim.service.ComboDaDatService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/api/")
public class ComboDaDatController extends BaseController {

    @Autowired
    ComboDaDatService comboDaDatService;

    @PostMapping(value = "themComboDaDat")
    @ResponseBody
    public ResponseEntity themComboDaDat(@RequestBody BaseRequestData<ComboDaDatReq> baseRequestData) {
        try {
            boolean response = comboDaDatService.themComboDaDat(baseRequestData);
            return success();
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "suaComboDaDat")
    @ResponseBody
    public ResponseEntity suaComboDaDat(@RequestBody BaseRequestData<ComboDaDatReq> baseRequestData) {
        try {
            boolean response = comboDaDatService.suaComboDaDat(baseRequestData);
            return success();
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "xoaComboDaDat")
    @ResponseBody
    public ResponseEntity xoaComboDaDat(@RequestBody BaseRequestData<ComboDaDatReq> baseRequestData) {
        try {
            boolean response = comboDaDatService.xoaComboDaDat(baseRequestData);
            return success();
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "timComboDaDat")
    @ResponseBody
    public ResponseEntity timComboDaDat(@RequestBody BaseRequestData<ComboDaDatReq> baseRequestData) {
        try {
            ComboDaDatRes response = comboDaDatService.timComboDaDat(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "chiTietComboDaDat")
    @ResponseBody
    public ResponseEntity chiTietComboDaDat(@RequestBody BaseRequestData<ComboDaDatReq> baseRequestData) {
        try {
            ComboDaDatRes response = comboDaDatService.chiTietComboDaDat(baseRequestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}
