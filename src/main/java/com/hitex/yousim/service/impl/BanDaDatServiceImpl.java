package com.hitex.yousim.service.impl;

import com.hitex.yousim.constant.Constant;
import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.bandadat.BanDaDatReq;
import com.hitex.yousim.dto.response.bandadat.BanDaDatRes;
import com.hitex.yousim.model.Ban;
import com.hitex.yousim.model.User;
import com.hitex.yousim.repository.BanDaDatRepository;
import com.hitex.yousim.repository.UserRepository;
import com.hitex.yousim.service.BanDaDatService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class BanDaDatServiceImpl implements BanDaDatService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BanDaDatRepository banDaDatRepository;

    @Override
    public BanDaDatRes danhSachBanTrong(BaseRequestData baseRequestData) throws ApplicationException {
        BanDaDatRes banDaDatRes = new BanDaDatRes();
        BanDaDatReq banDaDatReq = (BanDaDatReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN && user.getRoleId() != Constant.ROLE_STAFF) {
                throw new ApplicationException("ERR_0000009");
            }
            List<Ban> banList = banDaDatRepository.getListBanTrong(banDaDatReq.getDate());
            banDaDatRes.setBanList(banList);
        } catch (Exception e) {
            throw new ApplicationException("error");
        }
        return banDaDatRes;
    }

    @Override
    public BanDaDatRes danhSachBanDaDat(BaseRequestData baseRequestData) throws ApplicationException {
        BanDaDatRes banDaDatRes = new BanDaDatRes();
        BanDaDatReq banDaDatReq = (BanDaDatReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN && user.getRoleId() != Constant.ROLE_STAFF) {
                throw new ApplicationException("ERR_0000009");
            }
            List<Ban> banList = banDaDatRepository.getListBanDaDat(banDaDatReq.getDate());
            banDaDatRes.setBanList(banList);
        } catch (Exception e) {
            throw new ApplicationException("error");
        }
        return banDaDatRes;
    }
}
