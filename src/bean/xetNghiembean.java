package bean;

import java.sql.Date;

public class xetNghiembean {
	private String maXetNghiem;
	private String maBn;
	private Date ngayXN;
	private String loaiXN;
	private String ketQua;
	public xetNghiembean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public xetNghiembean(String maXetNghiem, String maBn, Date ngayXN, String loaiXN, String ketQua) {
		super();
		this.maXetNghiem = maXetNghiem;
		this.maBn = maBn;
		this.ngayXN = ngayXN;
		this.loaiXN = loaiXN;
		this.ketQua = ketQua;
	}
	public String getMaXetNghiem() {
		return maXetNghiem;
	}
	public void setMaXetNghiem(String maXetNghiem) {
		this.maXetNghiem = maXetNghiem;
	}
	public String getMaBn() {
		return maBn;
	}
	public void setMaBn(String maBn) {
		this.maBn = maBn;
	}
	public Date getNgayXN() {
		return ngayXN;
	}
	public void setNgayXN(Date ngayXN) {
		this.ngayXN = ngayXN;
	}
	public String getLoaiXN() {
		return loaiXN;
	}
	public void setLoaiXN(String loaiXN) {
		this.loaiXN = loaiXN;
	}
	public String getKetQua() {
		return ketQua;
	}
	public void setKetQua(String ketQua) {
		this.ketQua = ketQua;
	}
	
	
}
