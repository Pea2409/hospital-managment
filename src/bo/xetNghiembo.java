package bo;

import java.util.ArrayList;

import bean.xetNghiembean;
import dao.xetNghiemdao;

public class xetNghiembo {
	xetNghiemdao xndao = new xetNghiemdao();
	ArrayList<xetNghiembean> ds ;
	public ArrayList<xetNghiembean> getXN() throws Exception {
		ds = xndao.getXN();
		return ds;
	}
	public int soLuongXetNghiem() throws Exception{
		return xndao.soLuongXetNghiem();
	}
	public xetNghiembean getInfoXN(String maXetNghiem) throws Exception {
		return xndao.getInfoXN(maXetNghiem);
	}
	public void XoaXetNghiem(String maXetNghiem) throws Exception {
		xndao.XoaXetNghiem(maXetNghiem);
	}
	public void addXetNghiem(String maBn, String ngayTH, String loaiXN, String ketQua) throws Exception {
		xndao.addXetNghiem(maBn, ngayTH, loaiXN, ketQua);
	}
	public void SuaXN(String maBn, String ngayTH, String loaiXN, String ketQua, String maXN) throws Exception {
		xndao.SuaXN(maBn, ngayTH, loaiXN, ketQua, maXN);
	}
}
