package com.hitex.yousim.dto.request.bandadat;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.BanDaDat;
import lombok.Data;

import java.util.Date;

@Data
public class BanDaDatReq extends BanDaDat implements IRequestData {
    private String textSearch;
    private Date date;
    @Override
    public boolean isValid() {
        return false;
    }
}
