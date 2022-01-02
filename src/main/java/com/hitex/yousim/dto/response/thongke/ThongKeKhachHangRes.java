package com.hitex.yousim.dto.response.thongke;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.HoaDonKhachHang;
import com.hitex.yousim.model.ThongKeKhachHang;
import lombok.Data;

import java.util.List;

@Data
public class ThongKeKhachHangRes implements IResponseData {
    List<ThongKeKhachHang> thongKeKhachHangList;
    List<HoaDonKhachHang> hoaDonKhachHangList;
}
