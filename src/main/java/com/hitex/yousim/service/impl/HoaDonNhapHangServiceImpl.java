package com.hitex.yousim.service.impl;

import com.hitex.yousim.constant.Constant;
import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.hoadonnhaphang.HoaDonNhapHangReq;
import com.hitex.yousim.dto.response.hoadonnhaphang.HoaDonNhapHangRes;
import com.hitex.yousim.model.HoaDonNhapHang;
import com.hitex.yousim.model.PhieuNhap;
import com.hitex.yousim.model.User;
import com.hitex.yousim.repository.*;
import com.hitex.yousim.service.HoaDonNhapHangService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
public class HoaDonNhapHangServiceImpl implements HoaDonNhapHangService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    NhaCungCapRepository nhaCungCapRepository;
    @Autowired
    HoaDonNhapHangRepository hoaDonNhapHangRepository;
    @Autowired
    PhieuNhapRepository phieuNhapRepository;
    @Autowired
    TaiKhoanNhanVienRepository taiKhoanNhanVienRepository;

    @Override
    @Transactional
    public HoaDonNhapHangRes nhapNguyenLieu(BaseRequestData baseRequestData) throws ApplicationException {
        HoaDonNhapHangRes hoaDonNhapHangRes = new HoaDonNhapHangRes();
        HoaDonNhapHangReq hoaDonNhapHangReq = (HoaDonNhapHangReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN && user.getRoleId() != Constant.ROLE_STORAGE_STAFF) {
                throw new ApplicationException("ERR_0000009");
            }
            HoaDonNhapHang hoaDonNhapHang = new HoaDonNhapHang();
            hoaDonNhapHang.setNhacungcap(nhaCungCapRepository.getById(hoaDonNhapHangReq.getNhaCungCapId()));
            hoaDonNhapHang.setGhichu(hoaDonNhapHangReq.getGhiChu());
            hoaDonNhapHang.setTblTaiKhoanNhanVienId(user.getRoleId());
            hoaDonNhapHangRepository.save(hoaDonNhapHang);
            hoaDonNhapHangRepository.flush();
            hoaDonNhapHang.setTaikhoannhanvien(taiKhoanNhanVienRepository.getByNhanVienId(user.getRoleId()));
            hoaDonNhapHang.setPhieunhap(hoaDonNhapHangReq.getPhieuNhapList());
            float tongtien = 0;
            for(PhieuNhap pn : hoaDonNhapHangReq.getPhieuNhapList()) {
                pn.setTblHoaDonNhapHangId(hoaDonNhapHang.getId());
                phieuNhapRepository.save(pn);
                tongtien += pn.getDongia()*pn.getSoluong();
            }
            hoaDonNhapHang.setTongtien(tongtien);
            BeanUtils.copyProperties(hoaDonNhapHang,hoaDonNhapHangRes);
        } catch (ApplicationException e) {
            throw e;
        }
        return hoaDonNhapHangRes;
    }
}
