package com.hitex.yousim.service.impl;

import com.hitex.yousim.constant.Constant;
import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.nhaHang.NhaHangReq;
import com.hitex.yousim.dto.response.nhahang.NhaHangRes;
import com.hitex.yousim.model.NhaHang;
import com.hitex.yousim.model.User;
import com.hitex.yousim.repository.NhaHangRepository;
import com.hitex.yousim.repository.UserRepository;
import com.hitex.yousim.service.NhaHangService;
import com.hitex.yousim.utils.MessageUtils;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class NhaHangServiceImpl implements NhaHangService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    NhaHangRepository nhaHangRepository;
    @Override
    public boolean themNhaHang(BaseRequestData baseRequestData) throws ApplicationException {
        boolean success = false;
        NhaHangReq nhaHangReq = (NhaHangReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN) {
                throw new ApplicationException("ERR_0000009");
            }
            NhaHang nhaHang = new NhaHang();
            BeanUtils.copyProperties(nhaHangReq, nhaHang);
            nhaHangRepository.save(nhaHang);
            success = true;
        } catch (ApplicationException e) {
            throw e;
        }
        return success;
    }

    @Override
    public boolean suaNhaHang(BaseRequestData baseRequestData) throws ApplicationException {
        boolean success = false;
        NhaHangReq nhaHangReq = (NhaHangReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN) {
                throw new ApplicationException("ERR_0000009");
            }
            NhaHang nhaHang = nhaHangRepository.getById(nhaHangReq.getId());
            if (ObjectUtils.isEmpty(nhaHang)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("nha_hang"));
            }
            nhaHang.setTen(nhaHangReq.getTen());
            nhaHang.setDiachi(nhaHangReq.getDiachi());
            nhaHangRepository.save(nhaHang);
            success = true;
        } catch (ApplicationException e) {
            throw e;
        }
        return success;
    }
    @Override
    public boolean xoaNhaHang(BaseRequestData baseRequestData) throws ApplicationException {
        boolean success = false;
        NhaHangReq nhaHangReq = (NhaHangReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN) {
                throw new ApplicationException("ERR_0000009");
            }
            NhaHang nhaHang = nhaHangRepository.getById(nhaHangReq.getId());
            if (ObjectUtils.isEmpty(nhaHang)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("nha_hang"));
            }
            nhaHangRepository.delete(nhaHang);
            success = true;
        } catch (ApplicationException e) {
            throw e;
        }
        return success;
    }

    @Override
    public NhaHangRes chiTietNhaHang(BaseRequestData baseRequestData) throws ApplicationException {
        NhaHangRes nhaHangRes = new NhaHangRes();
        NhaHangReq nhaHangReq = (NhaHangReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN) {
                throw new ApplicationException("ERR_0000009");
            }
            NhaHang nhaHang = nhaHangRepository.getById(nhaHangReq.getId());
            if (ObjectUtils.isEmpty(nhaHang)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("nha_hang"));
            }
            List<NhaHang> nhaHangList = new ArrayList<>();
            nhaHangList.add(nhaHang);
            nhaHangRes.setNhaHangList(nhaHangList);
        } catch (ApplicationException e) {
            throw e;
        }
        return nhaHangRes;
    }

    @Override
    public NhaHangRes danhSachNhaHang(BaseRequestData baseRequestData) throws ApplicationException {
        NhaHangRes nhaHangRes = new NhaHangRes();
        NhaHangReq nhaHangReq = (NhaHangReq) baseRequestData.getWsRequest();
        try {
            List<NhaHang> nhaHangList = nhaHangRepository.getListNhaHang(nhaHangReq.getTextSearch());
            nhaHangRes.setNhaHangList(nhaHangList);
        } catch (Exception e) {
            throw new ApplicationException("error");
        }
        return nhaHangRes;
    }
}
