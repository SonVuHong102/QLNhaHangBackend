package com.hitex.yousim.service.impl;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.ban.BanRes;
import com.hitex.yousim.model.Ban;
import com.hitex.yousim.repository.BanRepository;
import com.hitex.yousim.repository.UserRepository;
import com.hitex.yousim.service.BanService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BanServiceImpl implements BanService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BanRepository banRepository;

    @Override
    public BanRes danhSachBan(BaseRequestData baseRequestData) throws ApplicationException {
        BanRes banRes = new BanRes();
        try {
            List<Ban> banList = banRepository.getListBan();
            banRes.setBanList(banList);
        } catch (Exception e) {
            throw new ApplicationException("error");
        }
        return banRes;
    }
}
