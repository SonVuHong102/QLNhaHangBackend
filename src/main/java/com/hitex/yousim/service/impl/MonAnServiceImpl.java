package com.hitex.yousim.service.impl;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.monan.MonAnReq;
import com.hitex.yousim.dto.response.monan.MonAnRes;
import com.hitex.yousim.model.MonAn;
import com.hitex.yousim.model.User;
import com.hitex.yousim.repository.ComboMonAnRepository;
import com.hitex.yousim.repository.MonAnRepository;
import com.hitex.yousim.repository.UserRepository;
import com.hitex.yousim.service.MonAnService;
import com.hitex.yousim.utils.MessageUtils;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
@Service
public class MonAnServiceImpl implements MonAnService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MonAnRepository monAnRepository;
    @Autowired
    ComboMonAnRepository comboMonAnRepository;

    @Override
    public MonAnRes timMonAn(BaseRequestData baseRequestData) throws ApplicationException {
        MonAnRes monAnRes = new MonAnRes();
        MonAnReq monAnReq = (MonAnReq) baseRequestData.getWsRequest();
        try {
            List<MonAn> monAnList = monAnRepository.getListMonAn(monAnReq.getTextSearch());
            monAnRes.setMonAnList(monAnList);
        } catch (Exception e) {
            throw new ApplicationException("error");
        }
        return monAnRes;
    }

    @Override
    public MonAnRes chiTietMonAn(BaseRequestData baseRequestData) throws ApplicationException {
        MonAnRes monAnRes = new MonAnRes();
        MonAnReq monAnReq = (MonAnReq) baseRequestData.getWsRequest();
        try {
            MonAn monAn = monAnRepository.getById(monAnReq.getId());
            if (ObjectUtils.isEmpty(monAn)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("mon_an"));
            }
            monAnRes.setMonAnList(List.of(monAn));
        } catch (Exception e) {
            throw new ApplicationException("error");
        }
        return monAnRes;
    }

    @Override
    public MonAnRes danhSachMonAn(BaseRequestData baseRequestData) throws ApplicationException {
        MonAnRes monAnRes = new MonAnRes();
        try {
            List<MonAn> monAnList = monAnRepository.getListMonAn();
            monAnRes.setMonAnList(monAnList);
        } catch (Exception e) {
            throw new ApplicationException("error");
        }
        return monAnRes;
    }

    @Override
    public boolean themMonAn(BaseRequestData baseRequestData) throws ApplicationException {
        boolean success = false;
        MonAnReq monAnReq = (MonAnReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            MonAn monAn = new MonAn();
            BeanUtils.copyProperties(monAnReq,monAn);
            monAnRepository.save(monAn);
            success = true;
        } catch (ApplicationException e) {
            throw e;
        }
        return success;
    }

    @Override
    public boolean suaMonAn(BaseRequestData baseRequestData) throws ApplicationException {
        boolean success = false;
        MonAnReq monAnReq = (MonAnReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            MonAn monAn = monAnRepository.getById(monAnReq.getId());
            if(ObjectUtils.isEmpty(monAn)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("mon_an"));
            }
            if(comboMonAnRepository.getListMonAnbyMonAn(monAn.getId()).isEmpty()) {
                throw new ApplicationException("ERR_0000010");
            }
            monAn.setTen(monAnReq.getTen());
            monAn.setGia(monAnReq.getGia());
            monAn.setMota(monAnReq.getMota());
            monAnRepository.save(monAn);
            success = true;
        } catch (ApplicationException e) {
            throw e;
        }
        return success;
    }

    @Override
    public boolean xoaMonAn(BaseRequestData baseRequestData) throws ApplicationException {
        boolean success = false;
        MonAnReq monAnReq = (MonAnReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
//            if (ObjectUtils.isEmpty(user)) {
//                throw new ApplicationException("ERR_0000003");
//            }
            MonAn monAn = monAnRepository.getById(monAnReq.getId());
            if(ObjectUtils.isEmpty(monAn)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("mon_an"));
            }
            if(!comboMonAnRepository.getListMonAnbyMonAn(monAn.getId()).isEmpty()) {
                throw new ApplicationException("ERR_0000010");
            }
            monAnRepository.delete(monAn);
            success = true;
        } catch (ApplicationException e) {
            throw e;
        }
        return success;
    }
}
