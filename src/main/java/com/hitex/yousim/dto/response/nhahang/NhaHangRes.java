package com.hitex.yousim.dto.response.nhahang;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.NhaHang;
import lombok.Data;

import java.util.List;

@Data
public class NhaHangRes implements IResponseData {
    List<NhaHang> nhaHangList;
}
