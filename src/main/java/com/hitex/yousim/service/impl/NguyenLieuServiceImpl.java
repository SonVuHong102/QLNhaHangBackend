package com.hitex.yousim.service.impl;

import com.hitex.yousim.constant.Constant;
import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.nguyenlieu.NguyenLieuReq;
import com.hitex.yousim.dto.response.nguyenlieu.NguyenLieuRes;
import com.hitex.yousim.model.NguyenLieu;
import com.hitex.yousim.model.User;
import com.hitex.yousim.repository.NguyenLieuRepository;
import com.hitex.yousim.repository.UserRepository;
import com.hitex.yousim.service.NguyenLieuService;
import com.hitex.yousim.utils.MessageUtils;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class NguyenLieuServiceImpl implements NguyenLieuService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    NguyenLieuRepository nguyenLieuRepository;


    @Override
    public NguyenLieuRes timNguyenLieu(BaseRequestData baseRequestData) throws ApplicationException {
        NguyenLieuRes nguyenLieuRes = new NguyenLieuRes();
        NguyenLieuReq nguyenLieuReq = (NguyenLieuReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN && user.getRoleId() != Constant.ROLE_STORAGE_STAFF) {
                throw new ApplicationException("ERR_0000009");
            }
            List<NguyenLieu> nguyenLieuList = nguyenLieuRepository.getListNguyenLieu(nguyenLieuReq.getTextSearch());
            nguyenLieuRes.setNguyenLieuList(nguyenLieuList);
        } catch (Exception e) {
            throw new ApplicationException("error");
        }
        return nguyenLieuRes;
    }

    @Override
    public NguyenLieuRes chiTietNguyenLieu(BaseRequestData baseRequestData) throws ApplicationException {
        NguyenLieuRes nguyenLieuRes = new NguyenLieuRes();
        NguyenLieuReq nguyenLieuReq = (NguyenLieuReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN && user.getRoleId() != Constant.ROLE_STORAGE_STAFF) {
                throw new ApplicationException("ERR_0000009");
            }
            NguyenLieu nguyenLieu = nguyenLieuRepository.getById(nguyenLieuReq.getId());
            if (ObjectUtils.isEmpty(nguyenLieu)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("nguyen_lieu"));
            }
            nguyenLieuRes.setNguyenLieuList(List.of(nguyenLieu));
        } catch (Exception e) {
            throw new ApplicationException("error");
        }
        return nguyenLieuRes;
    }

    @Override
    public NguyenLieuRes danhSachNguyenLieu(BaseRequestData baseRequestData) throws ApplicationException {
        NguyenLieuRes nguyenLieuRes = new NguyenLieuRes();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN && user.getRoleId() != Constant.ROLE_STORAGE_STAFF) {
                throw new ApplicationException("ERR_0000009");
            }
            List<NguyenLieu> nguyenLieuList = nguyenLieuRepository.getListNguyenLieu();
            nguyenLieuRes.setNguyenLieuList(nguyenLieuList);
        } catch (Exception e) {
            throw e;
        }
        return nguyenLieuRes;
    }

    @Override
    public boolean themNguyenLieu(BaseRequestData baseRequestData) throws ApplicationException {
        boolean success = false;
        NguyenLieuReq nguyenLieuReq = (NguyenLieuReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN && user.getRoleId() != Constant.ROLE_STORAGE_STAFF) {
                throw new ApplicationException("ERR_0000009");
            }
            NguyenLieu nguyenLieu = new NguyenLieu();
            BeanUtils.copyProperties(nguyenLieuReq, nguyenLieu);
            nguyenLieuRepository.save(nguyenLieu);
            success = true;
        } catch (ApplicationException e) {
            throw e;
        }
        return success;
    }

    @Override
    public boolean suaNguyenLieu(BaseRequestData baseRequestData) throws ApplicationException {
        boolean success = false;
        NguyenLieuReq nguyenLieuReq = (NguyenLieuReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN && user.getRoleId() != Constant.ROLE_STORAGE_STAFF) {
                throw new ApplicationException("ERR_0000009");
            }
            NguyenLieu nguyenLieu = nguyenLieuRepository.getById(nguyenLieuReq.getId());
            if(ObjectUtils.isEmpty(nguyenLieu)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("nguyen_lieu"));
            }
            nguyenLieu.setTen(nguyenLieuReq.getTen());
            nguyenLieu.setMota(nguyenLieuReq.getMota());
            nguyenLieu.setDongia(nguyenLieuReq.getDongia());
            nguyenLieu.setHansudung(nguyenLieuReq.getHansudung());
            nguyenLieu.setLoai(nguyenLieuReq.getLoai());
            nguyenLieuRepository.save(nguyenLieu);
            success = true;
        } catch (ApplicationException e) {
            throw e;
        }
        return success;
    }

    @Override
    public boolean xoaNguyenLieu(BaseRequestData baseRequestData) throws ApplicationException {
        boolean success = false;
        NguyenLieuReq nguyenLieuReq = (NguyenLieuReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN && user.getRoleId() != Constant.ROLE_STORAGE_STAFF) {
                throw new ApplicationException("ERR_0000009");
            }
            NguyenLieu nguyenLieu = nguyenLieuRepository.getById(nguyenLieuReq.getId());
            if(ObjectUtils.isEmpty(nguyenLieu)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("nguyen_lieu"));
            }
            nguyenLieuRepository.delete(nguyenLieu);
            success = true;
        } catch (ApplicationException e) {
            throw e;
        }
        return success;
    }
}
