package com.hitex.yousim.dto.request.nhacungcap;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.NhaCungCap;
import lombok.Data;

@Data
public class NhaCungCapReq extends NhaCungCap implements IRequestData {
    private String textSearch;
    @Override
    public boolean isValid() {
        return false;
    }
}
