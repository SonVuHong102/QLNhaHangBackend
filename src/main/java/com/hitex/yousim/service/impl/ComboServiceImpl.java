package com.hitex.yousim.service.impl;

import com.hitex.yousim.constant.Constant;
import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.combo.ComboReq;
import com.hitex.yousim.dto.response.combo.ComboRes;
import com.hitex.yousim.model.Combo;
import com.hitex.yousim.model.Combo_MonAn;
import com.hitex.yousim.model.MonAn;
import com.hitex.yousim.model.User;
import com.hitex.yousim.repository.ComboMonAnRepository;
import com.hitex.yousim.repository.ComboRepository;
import com.hitex.yousim.repository.MonAnRepository;
import com.hitex.yousim.repository.UserRepository;
import com.hitex.yousim.service.ComboService;
import com.hitex.yousim.utils.MessageUtils;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComboServiceImpl implements ComboService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ComboRepository comboRepository;
    @Autowired
    ComboMonAnRepository comboMonAnRepository;
    @Autowired
    MonAnRepository monAnRepository;

    @Override
    public ComboRes timCombo(BaseRequestData baseRequestData) throws ApplicationException {
        ComboRes comboRes = new ComboRes();
        ComboReq comboReq = (ComboReq) baseRequestData.getWsRequest();
        try {
            List<Combo> comboList = comboRepository.getListCombo(comboReq.getTextSearch());
            for(Combo combo : comboList) {
                List<MonAn> monAnList = comboMonAnRepository.getListMonAnByCombo(combo.getId());
                combo.setMonan(monAnList);
            }
            comboRes.setComboList(comboList);
        } catch (Exception e) {
            throw new ApplicationException("error");
        }
        return comboRes;
    }

    @Override
    public ComboRes chiTietCombo(BaseRequestData baseRequestData) throws ApplicationException {
        ComboRes comboRes = new ComboRes();
        ComboReq comboReq = (ComboReq) baseRequestData.getWsRequest();
        try {
            Combo combo = comboRepository.getById(comboReq.getId());
            if (ObjectUtils.isEmpty(combo)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("combo"));
            }
            List<MonAn> monAnList = comboMonAnRepository.getListMonAnByCombo(combo.getId());
            combo.setMonan(monAnList);
            comboRes.setComboList(List.of(combo));
        } catch (Exception e) {
            throw new ApplicationException("error");
        }
        return comboRes;
    }

    @Override
    public ComboRes danhSachCombo(BaseRequestData baseRequestData) throws ApplicationException {
        ComboRes comboRes = new ComboRes();
        try {
            List<Combo> comboList = comboRepository.getListCombo();
            comboList.forEach(combo -> {
                List<MonAn> monAnList = comboMonAnRepository.getListMonAnByCombo(combo.getId());
                combo.setMonan(monAnList);
            });
            comboRes.setComboList(comboList);
        } catch (Exception e) {
            throw new ApplicationException("error");
        }
        return comboRes;
    }

    @Override
    public boolean themCombo(BaseRequestData baseRequestData) throws ApplicationException {
        boolean success = false;
        ComboReq comboReq = (ComboReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN) {
                throw new ApplicationException("ERR_0000009");
            }
            Combo combo = new Combo();
            BeanUtils.copyProperties(comboReq,combo);
            comboRepository.save(combo);

            List<Combo_MonAn> listComboMonAn = new ArrayList<>();
            for (MonAn monan:
                 combo.getMonan()) {
                Combo_MonAn cm = new Combo_MonAn();
                cm.setTblComboId(combo.getId());
                cm.setTblMonAnId(monan.getId());
                listComboMonAn.add(cm);
            }
            comboMonAnRepository.saveAll(listComboMonAn);

            success = true;
        } catch (ApplicationException e) {
            throw e;
        }
        return success;
    }

    @Override
    public boolean suaCombo(BaseRequestData baseRequestData) throws ApplicationException {
        boolean success = false;
        ComboReq comboReq = (ComboReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN) {
                throw new ApplicationException("ERR_0000009");
            }
            Combo combo = comboRepository.getById(comboReq.getId());
            if(ObjectUtils.isEmpty(combo)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("combo"));
            }
            combo.setTen(comboReq.getTen());
            combo.setGia(comboReq.getGia());
            combo.setMota(comboReq.getMota());
            combo.setMonan(comboReq.getMonan());
            comboMonAnRepository.deleteCombo(combo.getId());
            Combo_MonAn combo_monAn = new Combo_MonAn();
            combo_monAn.setTblComboId(combo.getId());
            for(MonAn monan : combo.getMonan()) {
                combo_monAn.setTblMonAnId(monan.getId());
                comboMonAnRepository.save(combo_monAn);
            }
            comboRepository.save(combo);
            success = true;
        } catch (ApplicationException e) {
            throw e;
        }
        return success;
    }

    @Override
    public boolean xoaCombo(BaseRequestData baseRequestData) throws ApplicationException {
        boolean success = false;
        ComboReq comboReq = (ComboReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(), baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            if(user.getRoleId() != Constant.ROLE_ADMIN) {
                throw new ApplicationException("ERR_0000009");
            }
            Combo combo = comboRepository.getById(comboReq.getId());
            if(ObjectUtils.isEmpty(combo)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("combo"));
            }
            comboMonAnRepository.deleteCombo(combo.getId());
            comboRepository.delete(combo);
            success = true;
        } catch (ApplicationException e) {
            throw e;
        }
        return success;
    }
}
