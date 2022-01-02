package com.hitex.yousim.dto.request.combo;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.Combo;
import lombok.Data;

@Data
public class ComboReq extends Combo implements IRequestData {
    private String textSearch;
    @Override
    public boolean isValid() {
        return false;
    }
}