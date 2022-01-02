package com.hitex.yousim.dto.request.monan;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.MonAn;
import lombok.Data;

@Data
public class MonAnReq extends MonAn implements IRequestData {
    private String textSearch;
    @Override
    public boolean isValid() {
        return false;
    }
}
