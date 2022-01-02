package com.hitex.yousim.dto.request.monandadat;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.MonAnDaDat;
import lombok.Data;

import java.util.List;

@Data
public class MonAnDaDatReq extends MonAnDaDat implements IRequestData {
    private String textSearch;
    private List<MonAnDaDat> monAnDaDatList;
    @Override
    public boolean isValid() {
        return false;
    }
}
