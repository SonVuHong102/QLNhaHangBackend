package com.hitex.yousim.dto.response.combodadat;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.ComboDaDat;
import lombok.Data;

import java.util.List;

@Data
public class ComboDaDatRes implements IResponseData {
    List<ComboDaDat> comboDaDatList;
}
