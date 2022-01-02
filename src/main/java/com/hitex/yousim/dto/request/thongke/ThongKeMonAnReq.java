package com.hitex.yousim.dto.request.thongke;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.ThongKeMonAn;
import lombok.Data;

import java.util.Date;

@Data
public class ThongKeMonAnReq extends ThongKeMonAn implements IRequestData {
    private int id;
    private Date startDate;
    private Date endDate;
    @Override
    public boolean isValid() {
        return false;
    }
}