package com.hitex.yousim.service.impl;

import com.hitex.yousim.constant.Constant;
import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.combadadat.ComboDaDatReq;
import com.hitex.yousim.dto.response.combodadat.ComboDaDatRes;
import com.hitex.yousim.model.ComboDaDat;
import com.hitex.yousim.model.User;
import com.hitex.yousim.repository.ComboDaDatRepository;
import com.hitex.yousim.repository.UserRepository;
import com.hitex.yousim.service.ComboDaDatService;
import com.hitex.yousim.utils.MessageUtils;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class ComboDaDatServiceImpl implements ComboDaDatService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ComboDaDatRepository comboDaDatRepository;

    @Override
    public ComboDaDatRes timComboDaDat(BaseRequestData baseRequestData) throws ApplicationException {
        ComboDaDatRes comboDaDatRes = new ComboDaDatRes();
        ComboDaDatReq comboDaDatReq = (ComboDaDatReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if(user.getRoleId() == Constant.ROLE_ADMIN || user.getRoleId() == Constant.ROLE_STAFF) {
                List<ComboDaDat> comboDaDatList = comboDaDatRepository.getListComboDaDatTheoDatBan(comboDaDatReq.getId());
                comboDaDatRes.setComboDaDatList(comboDaDatList);
            } else {
                throw new ApplicationException("ERR_0000009");
            }
        } catch (Exception e) {
            throw new ApplicationException("error");
        }
        return comboDaDatRes;
    }

    @Override
    public ComboDaDatRes chiTietComboDaDat(BaseRequestData baseRequestData) throws ApplicationException {
        ComboDaDatRes comboDaDatRes = new ComboDaDatRes();
        ComboDaDatReq comboDaDatReq = (ComboDaDatReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if(user.getRoleId() != Constant.ROLE_ADMIN && user.getRoleId() != Constant.ROLE_STAFF) {
                throw new ApplicationException("ERR_0000009");
            }
            ComboDaDat comboDaDat = comboDaDatRepository.getById(comboDaDatReq.getId());
            if (ObjectUtils.isEmpty(comboDaDat)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("mon_an_da_dat"));
            }
            comboDaDatRes.setComboDaDatList(List.of(comboDaDat));
        } catch (Exception e) {
            throw new ApplicationException("error");
        }
        return comboDaDatRes;
    }

    @Override
    public boolean themComboDaDat(BaseRequestData baseRequestData) throws ApplicationException {
        boolean success = false;
        ComboDaDatReq comboDaDatReq = (ComboDaDatReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN && user.getRoleId() != Constant.ROLE_STAFF) {
                throw new ApplicationException("ERR_0000009");
            }
            ComboDaDat comboDaDat = new ComboDaDat();
            BeanUtils.copyProperties(comboDaDatReq,comboDaDat);
            comboDaDatRepository.save(comboDaDat);
            success = true;
        } catch (ApplicationException e) {
            throw e;
        }
        return success;
    }

    @Override
    public boolean suaComboDaDat(BaseRequestData baseRequestData) throws ApplicationException {
        boolean success = false;
        ComboDaDatReq comboDaDatReq = (ComboDaDatReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN && user.getRoleId() != Constant.ROLE_STAFF) {
                throw new ApplicationException("ERR_0000009");
            }
            ComboDaDat comboDaDat = comboDaDatRepository.getById(comboDaDatReq.getId());
            if(ObjectUtils.isEmpty(comboDaDat)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("mon_an_da_dat"));
            }
            comboDaDat.setGia(comboDaDatReq.getGia());
            comboDaDat.setKhuyenmai(comboDaDatReq.getKhuyenmai());
            comboDaDat.setSoluong(comboDaDatReq.getSoluong());
            comboDaDat.setTblBanDaDatId(comboDaDat.getTblBanDaDatId());
            comboDaDatRepository.save(comboDaDat);
            success = true;
        } catch (ApplicationException e) {
            throw e;
        }
        return success;
    }

    @Override
    public boolean xoaComboDaDat(BaseRequestData baseRequestData) throws ApplicationException {
        boolean success = false;
        ComboDaDatReq comboDaDatReq = (ComboDaDatReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN && user.getRoleId() != Constant.ROLE_STAFF) {
                throw new ApplicationException("ERR_0000009");
            }
            ComboDaDat comboDaDat = comboDaDatRepository.getById(comboDaDatReq.getId());
            if(ObjectUtils.isEmpty(comboDaDat)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("mon_an_da_dat"));
            }
            comboDaDatRepository.delete(comboDaDat);
            success = true;
        } catch (ApplicationException e) {
            throw e;
        }
        return success;
    }
}
