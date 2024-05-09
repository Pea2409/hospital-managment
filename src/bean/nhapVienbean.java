package bean;

import java.sql.Date;

public class nhapVienbean {
	private String maBN;
	private Date ngayNV;
	private Date ngayXV;
	private String chuanDoan;
	private String tieuSu;
	private String maNhapVien;
	public nhapVienbean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public nhapVienbean(String maBN, Date ngayNV, Date ngayXV, String chuanDoan, String tieuSu, String maNhapVien) {
		super();
		this.maBN = maBN;
		this.ngayNV = ngayNV;
		this.ngayXV = ngayXV;
		this.chuanDoan = chuanDoan;
		this.tieuSu = tieuSu;
		this.maNhapVien = maNhapVien;
	}
	public String getMaBN() {
		return maBN;
	}
	public void setMaBN(String maBN) {
		this.maBN = maBN;
	}
	public Date getNgayNV() {
		return ngayNV;
	}
	public void setNgayNV(Date ngayNV) {
		this.ngayNV = ngayNV;
	}
	public Date getNgayXV() {
		return ngayXV;
	}
	public void setNgayXV(Date ngayXV) {
		this.ngayXV = ngayXV;
	}
	public String getChuanDoan() {
		return chuanDoan;
	}
	public void setChuanDoan(String chuanDoan) {
		this.chuanDoan = chuanDoan;
	}
	public String getTieuSu() {
		return tieuSu;
	}
	public void setTieuSu(String tieuSu) {
		this.tieuSu = tieuSu;
	}
	public String getMaNhapVien() {
		return maNhapVien;
	}
	public void setMaNhapVien(String maNhapVien) {
		this.maNhapVien = maNhapVien;
	}
	
}
