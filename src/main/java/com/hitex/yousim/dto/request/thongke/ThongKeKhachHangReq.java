package com.hitex.yousim.dto.request.thongke;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.ThongKeKhachHang;
import lombok.Data;

import java.util.Date;

@Data
public class ThongKeKhachHangReq extends ThongKeKhachHang implements IRequestData {
    private int id;
    private Date startDate;
    private Date endDate;
    @Override
    public boolean isValid() {
        return false;
    }
}
