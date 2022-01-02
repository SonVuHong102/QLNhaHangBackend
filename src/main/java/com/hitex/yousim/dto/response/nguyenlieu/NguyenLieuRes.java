package com.hitex.yousim.dto.response.nguyenlieu;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.NguyenLieu;
import lombok.Data;

import java.util.List;

@Data
public class NguyenLieuRes implements IResponseData {
    List<NguyenLieu> nguyenLieuList;
}
