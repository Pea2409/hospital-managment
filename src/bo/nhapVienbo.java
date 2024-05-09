package bo;

import java.util.ArrayList;

import bean.nhapVienbean;
import dao.nhapViendao;

public class nhapVienbo {
	nhapViendao nvdao = new nhapViendao();
	public ArrayList<nhapVienbean> getNV() throws Exception {
		return nvdao.getNV();
	}
	public void addNhapVien(String maBn, String ngayNV, String ngayXV, String chuanDoan, String tieuSu) throws Exception {
		nvdao.addNhapVien(maBn, ngayNV, ngayXV, chuanDoan, tieuSu);
	}
	public void XoaNhapVien(String maNhapVien) throws Exception {
		nvdao.XoaNhapVien(maNhapVien);
	}
	public nhapVienbean getInfoNV(String mabn) throws Exception {
		return nvdao.getInfoNV(mabn);
	}
	public void SuaNV(String maBn, String ngayVao, String ngayRa, String chuanDoan, String tieuSu, String maNhapVien) throws Exception {
		nvdao.SuaNV(maBn, ngayVao, ngayRa, chuanDoan, tieuSu, maNhapVien);
	}
}
