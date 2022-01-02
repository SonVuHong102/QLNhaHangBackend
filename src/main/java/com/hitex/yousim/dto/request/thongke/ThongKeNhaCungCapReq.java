package com.hitex.yousim.dto.request.thongke;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.ThongKeNhaCungCap;
import lombok.Data;

import java.util.Date;

@Data
public class ThongKeNhaCungCapReq extends ThongKeNhaCungCap implements IRequestData {
    private Date startDate;
    private Date endDate;
    @Override
    public boolean isValid() {
        return false;
    }
}