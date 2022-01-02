package com.hitex.yousim.dto.request.ban;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.Ban;
import lombok.Data;

@Data
public class BanReq extends Ban implements IRequestData {
    private String textSearch;
    @Override
    public boolean isValid() {
        return false;
    }
}
