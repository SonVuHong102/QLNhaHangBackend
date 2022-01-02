package com.hitex.yousim.dto.response.monandadat;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.MonAnDaDat;
import lombok.Data;

import java.util.List;

@Data
public class MonAnDaDatRes implements IResponseData {
    List<MonAnDaDat> monAnDaDatList;
}
