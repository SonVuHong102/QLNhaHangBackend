package com.hitex.yousim.dto.request.thongke;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.ThongKeNguyenLieu;
import lombok.Data;

import java.util.Date;

@Data
public class ThongKeNguyenLieuReq extends ThongKeNguyenLieu implements IRequestData {
    private Date startDate;
    private Date endDate;
    @Override
    public boolean isValid() {
        return false;
    }
}
