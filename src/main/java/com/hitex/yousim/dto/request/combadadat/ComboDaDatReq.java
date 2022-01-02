package com.hitex.yousim.dto.request.combadadat;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.ComboDaDat;
import lombok.Data;

import java.util.List;

@Data
public class ComboDaDatReq extends ComboDaDat implements IRequestData {
    private String textSearch;
    private List<ComboDaDat> comboDaDatList;
    @Override
    public boolean isValid() {
        return false;
    }
}
