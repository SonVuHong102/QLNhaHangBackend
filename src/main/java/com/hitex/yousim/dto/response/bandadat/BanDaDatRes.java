package com.hitex.yousim.dto.response.bandadat;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.Ban;
import com.hitex.yousim.model.BanDaDat;
import lombok.Data;

import java.util.List;

@Data
public class BanDaDatRes implements IResponseData {
    List<Ban> BanList;
    List<BanDaDat> BanDaDatList;
}
