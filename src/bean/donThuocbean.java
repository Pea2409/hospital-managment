package bean;

import java.sql.Date;

public class donThuocbean {
	private String maDT;
	private String maBn;
	private Date ngayKeDon;
	private String maBS;
	private String tenThuoc;
	private String lieuDung;
	private String cachSD;
	public donThuocbean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public donThuocbean(String maDT, String maBn, Date ngayKeDon, String maBS, String tenThuoc, String lieuDung,
			String cachSD) {
		super();
		this.maDT = maDT;
		this.maBn = maBn;
		this.ngayKeDon = ngayKeDon;
		this.maBS = maBS;
		this.tenThuoc = tenThuoc;
		this.lieuDung = lieuDung;
		this.cachSD = cachSD;
	}
	public String getMaDT() {
		return maDT;
	}
	public void setMaDT(String maDT) {
		this.maDT = maDT;
	}
	public String getMaBn() {
		return maBn;
	}
	public void setMaBn(String maBn) {
		this.maBn = maBn;
	}
	public Date getNgayKeDon() {
		return ngayKeDon;
	}
	public void setNgayKeDon(Date ngayKeDon) {
		this.ngayKeDon = ngayKeDon;
	}
	public String getMaBS() {
		return maBS;
	}
	public void setMaBS(String maBS) {
		this.maBS = maBS;
	}
	public String getTenThuoc() {
		return tenThuoc;
	}
	public void setTenThuoc(String tenThuoc) {
		this.tenThuoc = tenThuoc;
	}
	public String getLieuDung() {
		return lieuDung;
	}
	public void setLieuDung(String lieuDung) {
		this.lieuDung = lieuDung;
	}
	public String getCachSD() {
		return cachSD;
	}
	public void setCachSD(String cachSD) {
		this.cachSD = cachSD;
	}
	
}
