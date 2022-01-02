package com.hitex.yousim.dto.response.monan;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.MonAn;
import lombok.Data;

import java.util.List;

@Data
public class MonAnRes implements IResponseData {
    List<MonAn> monAnList;
}
