package com.hitex.yousim.dto.response.thongke;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.HoaDonKhachHang;
import com.hitex.yousim.model.ThongKeMonAn;
import lombok.Data;

import java.util.List;

@Data
public class ThongKeMonAnRes implements IResponseData {
    List<ThongKeMonAn> thongKeMonAnList;
    List<HoaDonKhachHang> hoaDonKhachHangList;
}
