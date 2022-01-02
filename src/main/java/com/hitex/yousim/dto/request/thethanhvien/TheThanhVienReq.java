package com.hitex.yousim.dto.request.thethanhvien;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.TheThanhVien;
import lombok.Data;

@Data
public class TheThanhVienReq extends TheThanhVien implements IRequestData {
    private String textSearch;
    @Override
    public boolean isValid() {
        return false;
    }
}
