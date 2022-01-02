package com.hitex.yousim.dto.request.nguyenlieu;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.NguyenLieu;
import lombok.Data;

@Data
public class NguyenLieuReq extends NguyenLieu implements IRequestData {
    private String textSearch;
    @Override
    public boolean isValid() {
        return false;
    }
}
