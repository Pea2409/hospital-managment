package bo;

import java.sql.Date;
import java.util.ArrayList;

import bean.benhNhanbean;
import dao.benhNhandao;

public class benhNhanbo {
	benhNhandao bndao = new benhNhandao();
	ArrayList<benhNhanbean> ds ;
	
	public ArrayList<benhNhanbean> getBN() throws Exception {
		ds = bndao.getBN();
		return ds;
	}
	public ArrayList<benhNhanbean> Tim(String key) throws Exception {
	    ArrayList<benhNhanbean> tam = new ArrayList<benhNhanbean>();
	    for (benhNhanbean s: ds) {
			if ( s.getMaBN().trim().toLowerCase().contains(key.trim().toLowerCase())
			|| s.getTenBN().trim().toLowerCase().contains(key.trim().toLowerCase())
			|| s.getDiaChi().trim().toLowerCase().contains(key.trim().toLowerCase()))
				tam.add(s);
		}
	    return tam;
	}

	public benhNhanbean dangNhap(String maBHYT, String password) throws Exception {
		return bndao.dangNhapUser(maBHYT, password);
	}
	
	public boolean dangNhapAdmin(String username, String password) throws Exception {
		return bndao.dangNhapAdmin(username, password);
	}
	
	public void dangky (String mabn, String hodem, String tenbn, String ngaysinh, 
			String gioitinh , String DiaChi, String SoDT, String Password ) throws Exception{
		bndao.dangky(mabn, hodem, tenbn, ngaysinh, gioitinh, DiaChi, SoDT, Password);
	}
	public Boolean checkUsername(String mabn) throws Exception {
		return bndao.checkUsername(mabn);
	}
	
	public int soLuongBenhNhan() throws Exception{
		return bndao.soLuongBenhNhan();
	}
	public int soLuongSV() throws Exception{
		return bndao.soLuongSV();
	}
	public int soLuongHS() throws Exception{
		return bndao.soLuongHS();
	}
	public int soLuongGD() throws Exception{
		return bndao.soLuongGD();
	}
	public int soLuongTE() throws Exception{
		return bndao.soLuongTE();
	}
	public benhNhanbean infoUser(String maBHYT) throws Exception {
		return bndao.infoUser(maBHYT);
	}
	public void XoaBN(String maBn) throws Exception {
		bndao.XoaBN(maBn);
	}
	public void SuaBN(String mabn, String hodem, String tenbn, String ngaysinh, 
			String gioitinh , String DiaChi, String SoDT, String Password) throws Exception {
		bndao.SuaBN(mabn, hodem, tenbn, ngaysinh, gioitinh, DiaChi, SoDT, Password);
	}
}
