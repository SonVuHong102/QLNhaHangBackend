package com.hitex.yousim.service.impl;

import com.hitex.yousim.constant.Constant;
import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.datban.DatBanReq;
import com.hitex.yousim.dto.response.datban.DatBanRes;
import com.hitex.yousim.model.*;
import com.hitex.yousim.repository.*;
import com.hitex.yousim.service.DatBanService;
import com.hitex.yousim.utils.MessageUtils;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatBanServiceImpl implements DatBanService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    DatBanRepository datBanRepository;
    @Autowired
    BanRepository banRepository;
    @Autowired
    BanDaDatRepository banDaDatRepository;
    @Autowired
    ComboDaDatRepository comboDaDatRepository;
    @Autowired
    MonAnDaDatRepository monAnDaDatRepository;

    @Override
    public DatBanRes danhSachDatBan(BaseRequestData baseRequestData) throws ApplicationException {
        DatBanRes datBanRes = new DatBanRes();
        DatBanReq datBanReq = (DatBanReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN && user.getRoleId() != Constant.ROLE_STAFF) {
                throw new ApplicationException("ERR_0000009");
            }
            List<DatBan> datBanList = datBanRepository.findAll();
            datBanRes.setDatBanList(datBanList);
        } catch (Exception e) {
            throw e;
        }
        return datBanRes;
    }

    @Override
    public DatBanRes chiTietDatBan(BaseRequestData baseRequestData) throws ApplicationException {
        DatBanRes datBanRes = new DatBanRes();
        DatBanReq datBanReq = (DatBanReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN && user.getRoleId() != Constant.ROLE_STAFF) {
                throw new ApplicationException("ERR_0000009");
            }
            DatBan datBan = datBanRepository.getById(datBanReq.getId());
            datBanRes.setDatBanList(List.of(datBan));
        } catch (Exception e) {
            throw e;
        }
        return datBanRes;
    }

    @Override
    public boolean datBan(BaseRequestData baseRequestData) throws ApplicationException {
        boolean success = false;
        DatBanReq datBanReq = (DatBanReq) baseRequestData.getWsRequest();
        try {
            if(ObjectUtils.isEmpty(banDaDatRepository.checkBanDaDat(datBanReq.getBanId(),datBanReq.getNgaydat()))) {
                DatBan datBan = new DatBan();
                datBan.setTblTaiKhoanKhachHangId(datBanReq.getTaiKhoanKhachHangId());
                datBan.setGhichu(datBanReq.getGhichu());
                datBan.setNgaydat(datBanReq.getNgaydat());
                BanDaDat banDaDat = new BanDaDat();
                banDaDat.setTblBanId(datBanReq.getBanId());
                banDaDat.setNgaynhanban(datBanReq.getNgaynhanban());
                banDaDatRepository.save(banDaDat);
                banDaDatRepository.flush();
                datBan.setTblBanDaDatId(banDaDat.getId());
                datBanRepository.save(datBan);
                for(ComboDaDat c : datBanReq.getComboDaDatList()) {
                    c.setTblBanDaDatId(banDaDat.getId());
                    comboDaDatRepository.save(c);
                }
                for(MonAnDaDat m : datBanReq.getMonAnDaDatList()) {
                    m.setTblBanDaDatId(banDaDat.getId());
                    monAnDaDatRepository.save(m);
                }
                success = true;
            } else {
                throw new ApplicationException("ERR_0000007");
            }
        } catch (ApplicationException e) {
            throw new ApplicationException("error");
        }
        return success;
    }

//    @Override
//    public boolean suaDatBan(BaseRequestData baseRequestData) throws ApplicationException {
//        boolean success = false;
//        DatBanReq datBanReq = (DatBanReq) baseRequestData.getWsRequest();
//        try {
//            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
//            if (org.springframework.util.ObjectUtils.isEmpty(user)) {
//                throw new ApplicationException("ERR_0000003");
//            }
//            DatBan datBan = datBanRepository.getById(datBanReq.getId());
//            if(ObjectUtils.isEmpty(datBan)) {
//                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("dat_ban"));
//            }
//            datBan.setTblBanDaDatId(datBanReq.getTblBanDaDatId());
//            datBan.setBandadat(datBanReq.getBandadat());
//            datBan.setTaikhoankhachhang(datBanReq.getTaikhoankhachhang());
//            datBan.setNgaydat(datBanReq.getNgaydat());
//            datBan.setGhichu(datBanReq.getGhichu());
//            datBan.setTblTaiKhoanKhachHangId(datBanReq.getTblTaiKhoanKhachHangId());
//            datBanRepository.save(datBan);
//            success = true;
//        } catch (ApplicationException e) {
//            throw e;
//        }
//        return success;
//    }

    @Override
    public boolean xoaDatBan(BaseRequestData baseRequestData) throws ApplicationException {
        boolean success = false;
        DatBanReq datBanReq = (DatBanReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (org.springframework.util.ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            DatBan datBan = datBanRepository.getById(datBanReq.getId());
            if(org.springframework.util.ObjectUtils.isEmpty(datBan)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("dat_ban"));
            }
            comboDaDatRepository.deleteComboDaDatByBanDaDat(datBan.getTblBanDaDatId());
            monAnDaDatRepository.deleteMonAnDaDatByBanDaDat(datBan.getTblBanDaDatId());
            banDaDatRepository.delete(datBan.getBandadat());
            datBanRepository.delete(datBan);
            success = true;
        } catch (ApplicationException e) {
            throw e;
        }
        return success;
    }
}
