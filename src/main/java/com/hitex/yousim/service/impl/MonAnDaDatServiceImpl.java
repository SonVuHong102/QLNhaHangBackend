package com.hitex.yousim.service.impl;

import com.hitex.yousim.constant.Constant;
import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.monandadat.MonAnDaDatReq;
import com.hitex.yousim.dto.response.monandadat.MonAnDaDatRes;
import com.hitex.yousim.model.MonAnDaDat;
import com.hitex.yousim.model.User;
import com.hitex.yousim.repository.MonAnDaDatRepository;
import com.hitex.yousim.repository.UserRepository;
import com.hitex.yousim.service.MonAnDaDatService;
import com.hitex.yousim.utils.MessageUtils;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class MonAnDaDatServiceImpl  implements MonAnDaDatService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MonAnDaDatRepository monAnDaDatRepository;

    @Override
    public MonAnDaDatRes timMonAnDaDat(BaseRequestData baseRequestData) throws ApplicationException {
        MonAnDaDatRes monAnDaDatRes = new MonAnDaDatRes();
        MonAnDaDatReq monAnDaDatReq = (MonAnDaDatReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN && user.getRoleId() != Constant.ROLE_STAFF) {
                throw new ApplicationException("ERR_0000009");
            }
            List<MonAnDaDat> monAnDaDatList = monAnDaDatRepository.getListMonAnDaDat(monAnDaDatReq.getId());
            monAnDaDatRes.setMonAnDaDatList(monAnDaDatList);
        } catch (Exception e) {
            throw new ApplicationException("error");
        }
        return monAnDaDatRes;
    }

    @Override
    public MonAnDaDatRes chiTietMonAnDaDat(BaseRequestData baseRequestData) throws ApplicationException {
        MonAnDaDatRes monAnDaDatRes = new MonAnDaDatRes();
        MonAnDaDatReq monAnDaDatReq = (MonAnDaDatReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN && user.getRoleId() != Constant.ROLE_STAFF) {
                throw new ApplicationException("ERR_0000009");
            }
            MonAnDaDat monAnDaDat = monAnDaDatRepository.getById(monAnDaDatReq.getId());
            if (ObjectUtils.isEmpty(monAnDaDat)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("mon_an_da_dat"));
            }
            monAnDaDatRes.setMonAnDaDatList(List.of(monAnDaDat));
        } catch (Exception e) {
            throw new ApplicationException("error");
        }
        return monAnDaDatRes;
    }

    @Override
    public boolean themMonAnDaDat(BaseRequestData baseRequestData) throws ApplicationException {
        boolean success = false;
        MonAnDaDatReq monAnDaDatReq = (MonAnDaDatReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN && user.getRoleId() != Constant.ROLE_STAFF) {
                throw new ApplicationException("ERR_0000009");
            }
            MonAnDaDat monAnDaDat = new MonAnDaDat();
            BeanUtils.copyProperties(monAnDaDatReq,monAnDaDat);
            monAnDaDatRepository.save(monAnDaDat);
            success = true;
        } catch (ApplicationException e) {
            throw e;
        }
        return success;
    }

    @Override
    public boolean suaMonAnDaDat(BaseRequestData baseRequestData) throws ApplicationException {
        boolean success = false;
        MonAnDaDatReq monAnDaDatReq = (MonAnDaDatReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN && user.getRoleId() != Constant.ROLE_STAFF) {
                throw new ApplicationException("ERR_0000009");
            }
            MonAnDaDat monAnDaDat = monAnDaDatRepository.getById(monAnDaDatReq.getId());
            if(ObjectUtils.isEmpty(monAnDaDat)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("mon_an_da_dat"));
            }
            monAnDaDat.setGia(monAnDaDatReq.getGia());
            monAnDaDat.setKhuyenmai(monAnDaDatReq.getKhuyenmai());
            monAnDaDat.setSoluong(monAnDaDatReq.getSoluong());
            monAnDaDat.setTblBanDaDatId(monAnDaDat.getTblBanDaDatId());
            monAnDaDatRepository.save(monAnDaDat);
            success = true;
        } catch (ApplicationException e) {
            throw e;
        }
        return success;
    }

    @Override
    public boolean xoaMonAnDaDat(BaseRequestData baseRequestData) throws ApplicationException {
        boolean success = false;
        MonAnDaDatReq monAnDaDatReq = (MonAnDaDatReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN && user.getRoleId() != Constant.ROLE_STAFF) {
                throw new ApplicationException("ERR_0000009");
            }
            MonAnDaDat monAnDaDat = monAnDaDatRepository.getById(monAnDaDatReq.getId());
            if(ObjectUtils.isEmpty(monAnDaDat)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("mon_an_da_dat"));
            }
            monAnDaDatRepository.delete(monAnDaDat);
            success = true;
        } catch (ApplicationException e) {
            throw e;
        }
        return success;
    }
}

