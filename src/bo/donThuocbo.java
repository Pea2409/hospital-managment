package bo;

import java.util.ArrayList;

import bean.donThuocbean;
import bean.nhapVienbean;
import dao.donThuocdao;

public class donThuocbo {
	donThuocdao dndao = new donThuocdao();
	public ArrayList<donThuocbean> getDT() throws Exception {
		return dndao.getDT();
	}
	public void addDonThuoc(String maBn, String ngayKD, String maBs, String tenThuoc, String lieuDung, String cachSd) throws Exception {
		dndao.addDonThuoc(maBn, ngayKD, maBs, tenThuoc, lieuDung, cachSd);
	}
	public void XoaDonThuoc(String madt) throws Exception {
		dndao.XoaDonThuoc(madt);
	}
	public donThuocbean getInfoDT(String madt) throws Exception {
		return dndao.getInfoDT(madt);
	}
	public void SuaDT(String maBn, String ngayKD, String maBs, String tenThuoc, String lieuDung, String cachSd, String madt) throws Exception {
		dndao.SuaDT(maBn, ngayKD, maBs, tenThuoc, lieuDung, cachSd, madt);
	}
}
