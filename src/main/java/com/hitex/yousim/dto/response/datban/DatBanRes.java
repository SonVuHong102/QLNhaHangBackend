package com.hitex.yousim.dto.response.datban;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.DatBan;
import lombok.Data;

import java.util.List;

@Data
public class DatBanRes implements IResponseData {
    List<DatBan> DatBanList;
}
