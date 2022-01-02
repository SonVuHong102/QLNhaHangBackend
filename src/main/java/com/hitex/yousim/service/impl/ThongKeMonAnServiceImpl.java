package com.hitex.yousim.service.impl;

import com.hitex.yousim.constant.Constant;
import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.thongke.ThongKeMonAnReq;
import com.hitex.yousim.dto.response.thongke.ThongKeMonAnRes;
import com.hitex.yousim.model.*;
import com.hitex.yousim.repository.*;
import com.hitex.yousim.service.ThongKeMonAnService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class ThongKeMonAnServiceImpl implements ThongKeMonAnService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    HoaDonKhachHangRepository hoaDonKhachHangRepository;
    @Autowired
    DatBanRepository datBanRepository;
    @Autowired
    BanDaDatRepository banDaDatRepository;
    @Autowired
    ComboDaDatRepository comboDaDatRepository;
    @Autowired
    ComboRepository comboRepository;
    @Autowired
    MonAnRepository monAnRepository;
    @Autowired
    MonAnDaDatRepository monAnDaDatRepository;

    @Override
    public ThongKeMonAnRes thongKeMonAn(BaseRequestData baseRequestData) throws ApplicationException {
        ThongKeMonAnReq thongKeMonAnReq = (ThongKeMonAnReq) baseRequestData.getWsRequest();
        ThongKeMonAnRes thongKeMonAnRes = new ThongKeMonAnRes();

        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN) {
                throw new ApplicationException("ERR_0000009");
            }
            Date startDate = thongKeMonAnReq.getStartDate();
            Date endDate = thongKeMonAnReq.getEndDate();
            List<HoaDonKhachHang> hoaDonKhachHangList = getListHoaDonKhachHang(startDate,endDate);
            HashMap<Integer,ThongKeMonAn> map = new HashMap<>();
            for(HoaDonKhachHang h : hoaDonKhachHangList) {
                BanDaDat banDaDat = h.getDatban().getBandadat();
                for(MonAnDaDat m : banDaDat.getMonandadat()) {
                    MonAn monAn = m.getMonan();
                    ThongKeMonAn thongKeMonAn;
                    if(!map.containsKey(monAn.getId())) {
                        thongKeMonAn = new ThongKeMonAn();
                        BeanUtils.copyProperties(monAn,thongKeMonAn);
                        thongKeMonAn.setTongdoanhthu(m.getGia());
                        thongKeMonAn.setTansuatgoi(1);
                    } else {
                        thongKeMonAn = map.get(monAn.getId());
                        thongKeMonAn.setTongdoanhthu(thongKeMonAn.getTongdoanhthu() + m.getGia());
                        thongKeMonAn.setTansuatgoi(thongKeMonAn.getTansuatgoi()+1);
                    }
                    map.put(monAn.getId(),thongKeMonAn);
                }
                for(ComboDaDat c : banDaDat.getCombodadat()) {
                    Combo combo = c.getCombo();
                    List<MonAn> list = combo.getMonan();
                    list.forEach(monAn -> {
                        ThongKeMonAn thongKeMonAn;
                        if(!map.containsKey(monAn.getId())) {
                            thongKeMonAn = new ThongKeMonAn();
                            BeanUtils.copyProperties(monAn,thongKeMonAn);
                            thongKeMonAn.setTongdoanhthu(monAn.getGia());
                            thongKeMonAn.setTansuatgoi(1);
                        } else {
                            thongKeMonAn = map.get(monAn.getId());
                            thongKeMonAn.setTongdoanhthu(thongKeMonAn.getTongdoanhthu() + monAn.getGia());
                            thongKeMonAn.setTansuatgoi(thongKeMonAn.getTansuatgoi()+1);
                        }
                        map.put(monAn.getId(),thongKeMonAn);
                    });
                }
            }
            thongKeMonAnRes.setThongKeMonAnList((List<ThongKeMonAn>) map.values());
        } catch (Exception e) {
            throw new ApplicationException("error");
        }
        return thongKeMonAnRes;
    }

    private List<HoaDonKhachHang> getListHoaDonKhachHang(Date startDate, Date endDate) {
        List<HoaDonKhachHang> hoaDonKhachHangList = hoaDonKhachHangRepository.getListHoaDonKhachHang(startDate,endDate);
        for(HoaDonKhachHang h : hoaDonKhachHangList) {
            DatBan datBan = datBanRepository.getById(h.getTblDatBanId());
            BanDaDat banDaDat = banDaDatRepository.getById(datBan.getTblBanDaDatId());
            float tongtien = 0;
            List<ComboDaDat> comboDaDatList = comboDaDatRepository.getListComboDaDatTheoDatBan(banDaDat.getId());
            for(ComboDaDat c : comboDaDatList) {
                Combo combo = comboRepository.getById(c.getTblComboId());
                List<MonAn> monAnList = monAnRepository.getListMonAnByComboId(combo.getId());
                combo.setMonan(monAnList);
                c.setCombo(combo);
                tongtien += c.getGia() - c.getKhuyenmai();
            }
            banDaDat.setCombodadat(comboDaDatList);
            List<MonAnDaDat> monAnDaDatList = monAnDaDatRepository.getListMonAnDaDat(banDaDat.getId());
            banDaDat.setMonandadat(monAnDaDatList);
            for(MonAnDaDat m : monAnDaDatList) {
                MonAn monAn = monAnRepository.getById(m.getTblMonAnId());
                m.setMonan(monAn);
                tongtien += m.getGia() - m.getKhuyenmai();
            }
            tongtien += banDaDat.getGia() - banDaDat.getKhuyenmai();
            datBan.setBandadat(banDaDat);
            banDaDat.setMonandadat(monAnDaDatList);
            h.setDatban(datBan);
            h.setTongtien(tongtien);
        }
        return hoaDonKhachHangList;
    }

}
