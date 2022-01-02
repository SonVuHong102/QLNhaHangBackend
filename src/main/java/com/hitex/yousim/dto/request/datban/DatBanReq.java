package com.hitex.yousim.dto.request.datban;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.ComboDaDat;
import com.hitex.yousim.model.DatBan;
import com.hitex.yousim.model.MonAnDaDat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DatBanReq extends DatBan implements IRequestData {
    private String textSearch;
    private int taiKhoanKhachHangId;
    private int banId;
    private Date ngaynhanban;
    private Date startDate;
    private Date endDate;
    private List<MonAnDaDat> monAnDaDatList;
    private List<ComboDaDat> comboDaDatList;

    @Override
    public boolean isValid() {
        return false;
    }
}
