package com.hitex.yousim.service.impl;

import com.hitex.yousim.constant.Constant;
import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.thongke.ThongKeKhachHangReq;
import com.hitex.yousim.dto.response.thongke.ThongKeKhachHangRes;
import com.hitex.yousim.model.*;
import com.hitex.yousim.repository.*;
import com.hitex.yousim.service.ThongKeKhachHangService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class ThongKeKhachHangServiceImpl implements ThongKeKhachHangService {

    @Autowired
    HoaDonKhachHangRepository hoaDonKhachHangRepository;
    @Autowired
    DatBanRepository datBanRepository;
    @Autowired
    TaiKhoanKhachHangRepository taiKhoanKhachHangRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BanDaDatRepository banDaDatRepository;
    @Autowired
    ComboDaDatRepository comboDaDatRepository;
    @Autowired
    MonAnDaDatRepository monAnDaDatRepository;

    @Override
    public ThongKeKhachHangRes thongKeKhachHang(BaseRequestData baseRequestData) throws ApplicationException {
        ThongKeKhachHangReq thongKeKhachHangReq = (ThongKeKhachHangReq) baseRequestData.getWsRequest();
        ThongKeKhachHangRes thongKeKhachHangRes = new ThongKeKhachHangRes();

        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN) {
                throw new ApplicationException("ERR_0000009");
            }
            Date startDate = thongKeKhachHangReq.getStartDate();
            Date endDate = thongKeKhachHangReq.getEndDate();
            List<HoaDonKhachHang> hoaDonKhachHangList = getListHoaDonKhachHang(startDate,endDate);
            HashMap<Integer,ThongKeKhachHang> map = new HashMap<>();
            for(HoaDonKhachHang h : hoaDonKhachHangList) {
                KhachHang khachHang = h.getDatban().getTaikhoankhachhang().getKhachhang();
                if(!map.containsKey(khachHang.getId())) {
                    ThongKeKhachHang thongKeKhachHang = new ThongKeKhachHang();
                    BeanUtils.copyProperties(khachHang,thongKeKhachHang);
                    thongKeKhachHang.setTongdoanhthu(h.getTongtien());
                    thongKeKhachHang.setTansuat(1);
                    map.put(khachHang.getId(),thongKeKhachHang);
                } else {
                    ThongKeKhachHang thongKeKhachHang = map.get(khachHang.getId());
                    thongKeKhachHang.setTongdoanhthu(thongKeKhachHang.getTongdoanhthu() + h.getTongtien());
                    thongKeKhachHang.setTansuat(thongKeKhachHang.getTansuat() + 1);
                    map.put(khachHang.getId(),thongKeKhachHang);
                }
            }
            thongKeKhachHangRes.setThongKeKhachHangList((List<ThongKeKhachHang>) map.values());
        } catch (Exception e) {
            throw new ApplicationException("error");
        }
        return thongKeKhachHangRes;
    }

    private List<HoaDonKhachHang> getListHoaDonKhachHang(Date startDate, Date endDate) {
        List<HoaDonKhachHang> hoaDonKhachHangList = hoaDonKhachHangRepository.getListHoaDonKhachHang(startDate,endDate);
        for(HoaDonKhachHang h : hoaDonKhachHangList) {
            DatBan datBan = datBanRepository.getById(h.getTblDatBanId());
            TaiKhoanKhachHang taiKhoanKhachHang = taiKhoanKhachHangRepository.getById(datBan.getTblTaiKhoanKhachHangId());
            User user = userRepository.getById(taiKhoanKhachHang.getTblKhachHangId());
            ThanhVien thanhVien = new ThanhVien();
            BeanUtils.copyProperties(user,thanhVien);
            taiKhoanKhachHang.setKhachhang((KhachHang) thanhVien);
            datBan.setTaikhoankhachhang(taiKhoanKhachHang);
            BanDaDat banDaDat = banDaDatRepository.getById(datBan.getTblBanDaDatId());
            float tongtien = 0;
            List<ComboDaDat> comboDaDatList = comboDaDatRepository.getListComboDaDatTheoDatBan(banDaDat.getId());
            banDaDat.setCombodadat(comboDaDatList);
            for(ComboDaDat c : comboDaDatList) {
                tongtien += c.getGia();
            }
            List<MonAnDaDat> monAnDaDatList = monAnDaDatRepository.getListMonAnDaDat(banDaDat.getId());
            banDaDat.setMonandadat(monAnDaDatList);
            for(MonAnDaDat m : monAnDaDatList) {
                tongtien += m.getGia();
            }
            tongtien += banDaDat.getGia();
            datBan.setBandadat(banDaDat);
            banDaDat.setMonandadat(monAnDaDatList);
            h.setDatban(datBan);
            h.setTongtien(tongtien);
        }
        return hoaDonKhachHangList;
    }

    @Override
    public ThongKeKhachHangRes thongKeChiTietKhachHang(BaseRequestData baseRequestData) throws ApplicationException {
        ThongKeKhachHangReq thongKeKhachHangReq = (ThongKeKhachHangReq) baseRequestData.getWsRequest();
        ThongKeKhachHangRes thongKeKhachHangRes = new ThongKeKhachHangRes();

        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN) {
                throw new ApplicationException("ERR_0000009");
            }
            int khachHangId = thongKeKhachHangReq.getId();
            Date startDate = thongKeKhachHangReq.getStartDate();
            Date endDate = thongKeKhachHangReq.getEndDate();
            List<HoaDonKhachHang> hoaDonKhachHangList = hoaDonKhachHangRepository.getListHoaDonKhachHang(khachHangId,startDate,endDate);
            thongKeKhachHangRes.setHoaDonKhachHangList(hoaDonKhachHangList);
        } catch (Exception e) {
            throw new ApplicationException("error");
        }
        return thongKeKhachHangRes;
    }

}
