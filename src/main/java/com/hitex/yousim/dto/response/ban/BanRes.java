package com.hitex.yousim.dto.response.ban;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.Ban;
import lombok.Data;

import java.util.List;

@Data
public class BanRes implements IResponseData {
    List<Ban> BanList;
}
