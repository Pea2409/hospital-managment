package bo;

import java.util.ArrayList;

import bean.bacSibean;
import bean.benhNhanbean;
import dao.bacSidao;

public class bacSibo {
	bacSidao bsdao = new bacSidao();
	ArrayList<bacSibean> ds ;
	public ArrayList<bacSibean> getBS() throws Exception {
		ds = bsdao.getBS();
		return ds;
	}
	public ArrayList<bacSibean> Tim(String key) throws Exception {
	    ArrayList<bacSibean> tam = new ArrayList<bacSibean>();
	    for (bacSibean s: ds) {
			if ( s.getMaBacSi().trim().toLowerCase().contains(key.trim().toLowerCase())
			|| s.getTenBacSi().trim().toLowerCase().contains(key.trim().toLowerCase())
			|| s.getNganh().trim().toLowerCase().contains(key.trim().toLowerCase()))
				tam.add(s);
		}
	    return tam;
	}
	
	public int soLuongBacSi() throws Exception{
		return bsdao.soLuongBacSi();
	}
	
	public void addBacSi(String mabs, String tenbs, String nganh,String anh) throws Exception {
		bsdao.addBacSi(mabs, tenbs, nganh, anh);
	}
	
	public Boolean checkMaBS(String mabs) throws Exception {
		return bsdao.checkMaBS(mabs);
	}
	
	public void XoaBS(String maBs) throws Exception {
		bsdao.XoaBS(maBs);
	}
	
	public void SuaBS(String maBs, String tenBs, String Nganh, String anh) throws Exception {
		bsdao.SuaBS(maBs, tenBs, Nganh, anh);
	}
	public bacSibean getInfoBS(String mabs) throws Exception {
		return bsdao.getInfoBS(mabs);
		
	}
}
