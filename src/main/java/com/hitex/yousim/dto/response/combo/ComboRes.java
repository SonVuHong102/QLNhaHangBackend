package com.hitex.yousim.dto.response.combo;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.Combo;
import lombok.Data;

import java.util.List;

@Data
public class ComboRes implements IResponseData {
    List<Combo> comboList;
}
