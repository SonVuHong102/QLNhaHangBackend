package com.hitex.yousim.service.impl;

import com.hitex.yousim.constant.Constant;
import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.hoadonkhachhang.HoaDonKhachHangReq;
import com.hitex.yousim.dto.response.hoadonkhachhang.HoaDonKhachHangRes;
import com.hitex.yousim.model.DatBan;
import com.hitex.yousim.model.HoaDonKhachHang;
import com.hitex.yousim.model.TaiKhoanNhanVien;
import com.hitex.yousim.model.User;
import com.hitex.yousim.repository.DatBanRepository;
import com.hitex.yousim.repository.HoaDonKhachHangRepository;
import com.hitex.yousim.repository.TaiKhoanNhanVienRepository;
import com.hitex.yousim.repository.UserRepository;
import com.hitex.yousim.service.HoaDonKhachHangService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class HoaDonKhachHangServiceImpl implements HoaDonKhachHangService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    HoaDonKhachHangRepository hoaDonKhachHangRepository;
    @Autowired
    DatBanRepository datBanRepository;
    @Autowired
    TaiKhoanNhanVienRepository taiKhoanNhanVienRepository;

    @Override
    public HoaDonKhachHangRes danhSachHoaDonKhachHang(BaseRequestData baseRequestData) throws ApplicationException {
        HoaDonKhachHangRes hoaDonKhachHangRes = new HoaDonKhachHangRes();
        HoaDonKhachHangReq hoaDonKhachHangReq = (HoaDonKhachHangReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (org.apache.commons.lang3.ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN && user.getRoleId() != Constant.ROLE_STAFF) {
                throw new ApplicationException("ERR_0000009");
            }
            List<HoaDonKhachHang> hoaDonKhachHangList = hoaDonKhachHangRepository.findAll();
            hoaDonKhachHangRes.setHoaDonKhachHangList(hoaDonKhachHangList);
        } catch (Exception e) {
            throw e;
        }
        return hoaDonKhachHangRes;
    }

    @Override
    public HoaDonKhachHangRes chiTietHoaDonKhachHang(BaseRequestData baseRequestData) throws ApplicationException {
        HoaDonKhachHangRes hoaDonKhachHangRes = new HoaDonKhachHangRes();
        HoaDonKhachHangReq hoaDonKhachHangReq = (HoaDonKhachHangReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (org.apache.commons.lang3.ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN && user.getRoleId() != Constant.ROLE_STAFF) {
                throw new ApplicationException("ERR_0000009");
            }
            HoaDonKhachHang hoaDonKhachHang = hoaDonKhachHangRepository.getById(hoaDonKhachHangReq.getId());
            hoaDonKhachHangRes.setHoaDonKhachHangList(List.of(hoaDonKhachHang));
        } catch (Exception e) {
            throw e;
        }
        return hoaDonKhachHangRes;
    }
    
    @Override
    public HoaDonKhachHangRes thanhToan(BaseRequestData baseRequestData) throws ApplicationException {
        HoaDonKhachHangRes hoaDonKhachHangRes = new HoaDonKhachHangRes();
        HoaDonKhachHangReq hoaDonKhachHangReq = (HoaDonKhachHangReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN && user.getRoleId() != Constant.ROLE_STAFF) {
                throw new ApplicationException("ERR_0000009");
            }
            DatBan datBan = datBanRepository.getById(hoaDonKhachHangReq.getDatBanId());
            if(ObjectUtils.isEmpty(datBan)) {
                throw new ApplicationException("ERR_0000003");
            }
            HoaDonKhachHang hoaDonKhachHang = new HoaDonKhachHang();
            BeanUtils.copyProperties(hoaDonKhachHangReq,hoaDonKhachHang);
            TaiKhoanNhanVien taiKhoanNhanVien = taiKhoanNhanVienRepository.getByNhanVienId(user.getRoleId());
            hoaDonKhachHang.setTaikhoannhanvien(taiKhoanNhanVien);
            hoaDonKhachHangRepository.save(hoaDonKhachHang);
            BeanUtils.copyProperties(hoaDonKhachHang,hoaDonKhachHangRes);
        } catch (ApplicationException e) {
            throw e;
        }
        return hoaDonKhachHangRes;
    }
}
