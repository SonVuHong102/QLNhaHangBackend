package com.hitex.yousim.dto.request.nhaHang;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.NhaHang;
import lombok.Data;
@Data
public class NhaHangReq extends NhaHang implements IRequestData {
   private String textSearch;
    @Override
    public boolean isValid() {
        return false;
    }
}
