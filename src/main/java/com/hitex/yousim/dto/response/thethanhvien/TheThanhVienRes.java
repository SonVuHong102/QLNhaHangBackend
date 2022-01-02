package com.hitex.yousim.dto.response.thethanhvien;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.TheThanhVien;
import lombok.Data;

import java.util.List;

@Data
public class TheThanhVienRes implements IResponseData {
    List<TheThanhVien> theThanhVienList;
}
