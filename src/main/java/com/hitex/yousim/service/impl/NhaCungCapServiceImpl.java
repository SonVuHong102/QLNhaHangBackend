package com.hitex.yousim.service.impl;

import com.hitex.yousim.constant.Constant;
import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.nhacungcap.NhaCungCapReq;
import com.hitex.yousim.dto.response.nhacungcap.NhaCungCapRes;
import com.hitex.yousim.model.NhaCungCap;
import com.hitex.yousim.model.User;
import com.hitex.yousim.repository.NhaCungCapRepository;
import com.hitex.yousim.repository.UserRepository;
import com.hitex.yousim.service.NhaCungCapService;
import com.hitex.yousim.utils.MessageUtils;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class NhaCungCapServiceImpl implements NhaCungCapService {
    @Autowired
    NhaCungCapRepository nhaCungCapRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public NhaCungCapRes timNhaCungCap(BaseRequestData baseRequestData) throws ApplicationException {
        NhaCungCapRes nhaCungCapRes = new NhaCungCapRes();
        NhaCungCapReq nhaCungCapReq = (NhaCungCapReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN) {
                throw new ApplicationException("ERR_0000009");
            }
            List<NhaCungCap> nhaCungCapList = nhaCungCapRepository.getListNhaCungCap(nhaCungCapReq.getTextSearch());
            nhaCungCapRes.setNhaCungCapList(nhaCungCapList);
        } catch (Exception e) {
            throw new ApplicationException("error");
        }
        return nhaCungCapRes;
    }

    @Override
    public NhaCungCapRes chiTietNhaCungCap(BaseRequestData baseRequestData) throws ApplicationException {
        NhaCungCapRes nhaCungCapRes = new NhaCungCapRes();
        NhaCungCapReq nhaCungCapReq = (NhaCungCapReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN) {
                throw new ApplicationException("ERR_0000009");
            }
            NhaCungCap nhaCungCap = nhaCungCapRepository.getById(nhaCungCapReq.getId());
            if (ObjectUtils.isEmpty(nhaCungCap)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("nha_cung_cap"));
            }
            nhaCungCapRes.setNhaCungCapList(List.of(nhaCungCap));
        } catch (Exception e) {
            throw new ApplicationException("error");
        }
        return nhaCungCapRes;
    }

    @Override
    public NhaCungCapRes danhSachNhaCungCap(BaseRequestData baseRequestData) throws ApplicationException {
        NhaCungCapRes nhaCungCapRes = new NhaCungCapRes();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN) {
                throw new ApplicationException("ERR_0000009");
            }
            List<NhaCungCap> nhaCungCapList = nhaCungCapRepository.getListNhaCungCap();
            nhaCungCapRes.setNhaCungCapList(nhaCungCapList);
        } catch (Exception e) {
            throw new ApplicationException("error");
        }
        return nhaCungCapRes;
    }

    @Override
    public boolean themNhaCungCap(BaseRequestData baseRequestData) throws ApplicationException {
        boolean success = false;
        NhaCungCapReq nhaCungCapReq = (NhaCungCapReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN) {
                throw new ApplicationException("ERR_0000009");
            }
            NhaCungCap nhaCungCap = new NhaCungCap();
            BeanUtils.copyProperties(nhaCungCapReq,nhaCungCap);
            nhaCungCapRepository.save(nhaCungCap);
            success = true;
        } catch (ApplicationException e) {
            throw e;
        }
        return success;
    }

    @Override
    public boolean suaNhaCungCap(BaseRequestData baseRequestData) throws ApplicationException {
        boolean success = false;
        NhaCungCapReq nhaCungCapReq = (NhaCungCapReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN) {
                throw new ApplicationException("ERR_0000009");
            }
            NhaCungCap nhaCungCap = nhaCungCapRepository.getById(nhaCungCapReq.getId());
            if(ObjectUtils.isEmpty(nhaCungCap)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("nha_cung_cap"));
            }
            nhaCungCap.setTen(nhaCungCapReq.getTen());
            nhaCungCap.setDiachi(nhaCungCapReq.getDiachi());
            nhaCungCap.setGhichu(nhaCungCapReq.getGhichu());
            nhaCungCapRepository.save(nhaCungCap);
            success = true;
        } catch (ApplicationException e) {
            throw e;
        }
        return success;
    }

    @Override
    public boolean xoaNhaCungCap(BaseRequestData baseRequestData) throws ApplicationException {
        boolean success = false;
        NhaCungCapReq nhaCungCapReq = (NhaCungCapReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN) {
                throw new ApplicationException("ERR_0000009");
            }
            NhaCungCap nhaCungCap = nhaCungCapRepository.getById(nhaCungCapReq.getId());
            if(ObjectUtils.isEmpty(nhaCungCap)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("nha_cung_cap"));
            }
            nhaCungCapRepository.delete(nhaCungCap);
            success = true;
        } catch (ApplicationException e) {
            throw e;
        }
        return success;
    }
}
