package com.hitex.yousim.service.impl;

import com.hitex.yousim.constant.Constant;
import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.thethanhvien.TheThanhVienReq;
import com.hitex.yousim.dto.response.thethanhvien.TheThanhVienRes;
import com.hitex.yousim.model.TheThanhVien;
import com.hitex.yousim.model.User;
import com.hitex.yousim.repository.TheThanhVienRepository;
import com.hitex.yousim.repository.UserRepository;
import com.hitex.yousim.service.TheThanhVienService;
import com.hitex.yousim.utils.MessageUtils;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class TheThanhVienServiceImpl implements TheThanhVienService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TheThanhVienRepository theThanhVienRepository;

    @Override
    public TheThanhVienRes timTheThanhVien(BaseRequestData baseRequestData) throws ApplicationException {
        TheThanhVienRes theThanhVienRes = new TheThanhVienRes();
        TheThanhVienReq theThanhVienReq = (TheThanhVienReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            List<TheThanhVien> theThanhVienList = theThanhVienRepository.getListTheThanhVien(theThanhVienReq.getTextSearch());
            theThanhVienRes.setTheThanhVienList(theThanhVienList);
        } catch (Exception e) {
            throw new ApplicationException("error");
        }
        return theThanhVienRes;
    }

    @Override
    public boolean themTheThanhVien(BaseRequestData baseRequestData) throws ApplicationException {
        boolean success = false;
        TheThanhVienReq theThanhVienReq = (TheThanhVienReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN && user.getRoleId() != Constant.ROLE_STAFF) {
                throw new ApplicationException("ERR_0000009");
            }
            TheThanhVien theThanhVien = new TheThanhVien();
            BeanUtils.copyProperties(theThanhVienReq,theThanhVien);
            theThanhVienRepository.save(theThanhVien);
            success = true;
        } catch (ApplicationException e) {
            throw e;
        }
        return success;
    }

    @Override
    public boolean suaTheThanhVien(BaseRequestData baseRequestData) throws ApplicationException {
        boolean success = false;
        TheThanhVienReq theThanhVienReq = (TheThanhVienReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN && user.getRoleId() != Constant.ROLE_STAFF) {
                throw new ApplicationException("ERR_0000009");
            }
            TheThanhVien theThanhVien = theThanhVienRepository.getById(theThanhVienReq.getId());
            if(ObjectUtils.isEmpty(theThanhVien)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("the_thanh_vien"));
            }
            theThanhVien.setLoai(theThanhVienReq.getLoai());
            theThanhVienRepository.save(theThanhVien);
            success = true;
        } catch (ApplicationException e) {
            throw e;
        }
        return success;
    }

    @Override
    public boolean xoaTheThanhVien(BaseRequestData baseRequestData) throws ApplicationException {
        boolean success = false;
        TheThanhVienReq theThanhVienReq = (TheThanhVienReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN && user.getRoleId() != Constant.ROLE_STAFF) {
                throw new ApplicationException("ERR_0000009");
            }
            TheThanhVien theThanhVien = theThanhVienRepository.getById(theThanhVienReq.getId());
            if(ObjectUtils.isEmpty(theThanhVien)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("the_thanh_vien"));
            }
            theThanhVienRepository.delete(theThanhVien);
            success = true;
        } catch (ApplicationException e) {
            throw e;
        }
        return success;
    }
}
