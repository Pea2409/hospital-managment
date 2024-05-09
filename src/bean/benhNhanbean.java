package bean;

import java.util.Date;

public class benhNhanbean {
	private String maBN;
	private String hoDem;
	private String tenBN;
	private Date ngaySinh;
	private String gioiTinh;
	private String diaChi;
	private String soDT;
	private String pass;
	public benhNhanbean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public benhNhanbean(String maBN, String hoDem, String tenBN, Date ngaySinh, String gioiTinh, String diaChi,
			String soDT, String pass) {
		super();
		this.maBN = maBN;
		this.hoDem = hoDem;
		this.tenBN = tenBN;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.soDT = soDT;
		this.pass = pass;
	}
	public String getMaBN() {
		return maBN;
	}
	public void setMaBN(String maBN) {
		this.maBN = maBN;
	}
	public String getHoDem() {
		return hoDem;
	}
	public void setHoDem(String hoDem) {
		this.hoDem = hoDem;
	}
	public String getTenBN() {
		return tenBN;
	}
	public void setTenBN(String tenBN) {
		this.tenBN = tenBN;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getSoDT() {
		return soDT;
	}
	public void setSoDT(String soDT) {
		this.soDT = soDT;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
