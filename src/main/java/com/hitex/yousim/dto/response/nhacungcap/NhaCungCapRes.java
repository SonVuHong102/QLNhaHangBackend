package com.hitex.yousim.dto.response.nhacungcap;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.NhaCungCap;
import lombok.Data;

import java.util.List;

@Data
public class NhaCungCapRes implements IResponseData {
    List<NhaCungCap> nhaCungCapList;
}
