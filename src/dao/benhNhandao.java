package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.benhNhanbean;


public class benhNhandao {
	public ArrayList<benhNhanbean> getBN() throws Exception {
		ArrayList<benhNhanbean>  ds = new ArrayList<benhNhanbean>();
		// b1: Kết nối vào csdl : QlBenhNhan
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		
		// b2: Tạo câu lệnh sql:
		String sql = "select * from BENHNHAN";
		
		// b3: Tạo câu lệnh
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		
		// b4: Truyền tham số vào câu lệnh nếu có
		
		// b5: Thực hiện câu lệnh
		ResultSet rs = cmd.executeQuery();
		
		// b6: Duyệt rs
		while (rs.next()) {
			String maBn = rs.getString("MaBHYT");
			String hoDem = rs.getString("HoDem");
			String tenBn = rs.getString("TenBN");
			Date ngaySinh = rs.getDate("NgaySinh");
			String gioiTinh = rs.getString("GioiTinh");
			String diaChi = rs.getString("DiaChi");
			String soDt = rs.getString("SoDT");
			String pass= rs.getString("Password");
			ds.add(new benhNhanbean(maBn, hoDem, tenBn, ngaySinh, gioiTinh, diaChi, soDt, pass));
		}
		
		// b7: Đóng các đối tượng
		rs.close();
		kn.cn.close();
		
		return ds;
	}
	
	public void dangky (String mabn, String hodem, String tenbn, String ngaysinh, 
		String gioitinh , String DiaChi, String SoDT, String Password ) throws Exception{
		KetNoi kn =new KetNoi();
		kn.ketnoi();
		// b2: Tạo câu lệnh sql:
		String sql= "INSERT INTO BENHNHAN ( MaBHYT, HoDem, TenBN, NgaySinh, GioiTinh, DiaChi, SoDT, Password) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ? ,? )";
		
		// b3: Tạo câu lệnh
		PreparedStatement cmd =kn.cn.prepareStatement(sql);
		cmd.setString(1, mabn);
		cmd.setString(2, hodem);
		cmd.setString(3, tenbn);
        cmd.setString(4, ngaysinh);
		cmd.setString(5, gioitinh);
		cmd.setString(6, DiaChi);
		cmd.setString(7, SoDT);
		cmd.setString(8, Password);
		cmd.executeUpdate();
	}
	
	public Boolean checkUsername(String mabn) throws Exception {
		// b1: Kết nối vào csdl : QlSach
		KetNoi kn =new KetNoi();
		kn.ketnoi();
		
		// b2: Tạo câu lệnh sql:
		String sql= "SELECT * FROM BENHNHAN where MaBHYT = ?";
		
		// b3: Tạo câu lệnh
		PreparedStatement ps = kn.cn.prepareStatement(sql);
		
		// b4: Truyền tham số vào câu lệnh nếu có
		ps.setString(1, mabn);
		
		// b5: Thực hiện câu lệnh
		ResultSet rs = ps.executeQuery();
		return rs.next();
	}
	
	public benhNhanbean dangNhapUser(String maBHYT, String password) throws Exception {
	    // b1: Kết nối vào cơ sở dữ liệu: QLBenhNhan
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();

	    // b2: Tạo câu lệnh SQL
	    String sql = "SELECT * FROM BENHNHAN WHERE MaBHYT = ? AND Password = ?";

	    // b3: Tạo câu lệnh
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setString(1, maBHYT);
	    cmd.setString(2, password);

	    // b4: Thực thi câu lệnh SQL và kiểm tra kết quả
	    ResultSet rs = cmd.executeQuery();
	    benhNhanbean bn = null;

	    while(rs.next()) {
	    	String maBn = rs.getString("MaBHYT");
			String hoDem = rs.getString("HoDem");
			String tenBn = rs.getString("TenBN");
			Date ngaySinh = rs.getDate("NgaySinh");
			String gioiTinh = rs.getString("GioiTinh");
			String diaChi = rs.getString("DiaChi");
			String soDt = rs.getString("SoDT");
			String pass= rs.getString("Password");
			bn = new benhNhanbean(maBn, hoDem, tenBn, ngaySinh, gioiTinh, diaChi, soDt, pass);
		}
	    
	    rs.close();
	    kn.cn.close();
	    return bn;
	}
	
	public boolean dangNhapAdmin(String username, String password) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		
		// b2: Tạo câu lệnh sql:
		String sql = "SELECT * FROM ADMIN WHERE Username = ? AND Password = ?";
		
		// b3: Tạo câu lệnh
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		
		// b4: Truyền tham số vào câu lệnh nếu có
        cmd.setString(1, username);
        cmd.setString(2, password);
		// b5: Thực hiện câu lệnh
		ResultSet rs = cmd.executeQuery();
		if (rs.next()) {
            // Nếu có bản ghi, tức là đăng nhập thành công
            return true;
        }
		return false;
	}
	
	public int soLuongBenhNhan() throws Exception{
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		int soluong = 0;
		
		// b2: Tạo câu lệnh sql:
		String sql = "SELECT COUNT(*) AS SoLuong FROM BENHNHAN;";
		
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			soluong = rs.getInt("SoLuong");
		}
		
		rs.close();
		kn.cn.close();
		return soluong;
	}
	
	public int soLuongSV() throws Exception{
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		int soluong = 0;
		
		// b2: Tạo câu lệnh sql:
		String sql = "SELECT COUNT(*) AS SoLuong FROM BENHNHAN WHERE MaBHYT LIKE 'SV%';";
		
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			soluong = rs.getInt("SoLuong");
		}
		
		rs.close();
		kn.cn.close();
		return soluong;
	}
	
	public int soLuongGD() throws Exception{
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		int soluong = 0;
		
		// b2: Tạo câu lệnh sql:
		String sql = "SELECT COUNT(*) AS SoLuong FROM BENHNHAN WHERE MaBHYT LIKE 'GD%';";
		
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			soluong = rs.getInt("SoLuong");
		}
		
		rs.close();
		kn.cn.close();
		return soluong;
	}
	
	public int soLuongTE() throws Exception{
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		int soluong = 0;
		
		// b2: Tạo câu lệnh sql:
		String sql = "SELECT COUNT(*) AS SoLuong FROM BENHNHAN WHERE MaBHYT LIKE 'TE%';";
		
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			soluong = rs.getInt("SoLuong");
		}
		
		rs.close();
		kn.cn.close();
		return soluong;
	}
	public int soLuongHS() throws Exception{
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		int soluong = 0;
		
		// b2: Tạo câu lệnh sql:
		String sql = "SELECT COUNT(*) AS SoLuong FROM BENHNHAN WHERE MaBHYT LIKE 'HS%';";
		
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			soluong = rs.getInt("SoLuong");
		}
		
		rs.close();
		kn.cn.close();
		return soluong;
	}

	
	public benhNhanbean infoUser(String maBHYT) throws Exception {
	    // b1: Kết nối vào cơ sở dữ liệu: QLBenhNhan
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();

	    // b2: Tạo câu lệnh SQL
	    String sql = "SELECT * FROM BENHNHAN WHERE MaBHYT =?";

	    // b3: Tạo câu lệnh
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setString(1, maBHYT);
	    // b4: Thực thi câu lệnh SQL và kiểm tra kết quả
	    ResultSet rs = cmd.executeQuery();
	    benhNhanbean bn = null;

	    while(rs.next()) {
	    	String maBn = rs.getString("MaBHYT");
			String hoDem = rs.getString("HoDem");
			String tenBn = rs.getString("TenBN");
			Date ngaySinh = rs.getDate("NgaySinh");
			String gioiTinh = rs.getString("GioiTinh");
			String diaChi = rs.getString("DiaChi");
			String soDt = rs.getString("SoDT");
			String pass= rs.getString("Password");
			bn = new benhNhanbean(maBn, hoDem, tenBn, ngaySinh, gioiTinh, diaChi, soDt, pass);
		}
	    
	    rs.close();
	    kn.cn.close();
	    return bn;
	}
	
	public void XoaBN(String maBn) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "delete FROM BENHNHAN where MaBHYT = ?";
		PreparedStatement ps = kn.cn.prepareStatement(sql);
		ps.setString(1,maBn);
		ps.executeUpdate();
		kn.cn.close();
	}

	public void SuaBN(String mabn, String hodem, String tenbn, String ngaysinh, 
			String gioitinh , String DiaChi, String SoDT, String Password) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "update BENHNHAN set HoDem = ?, TenBN= ?, NgaySinh =?, GioiTinh =?, DiaChi =?, SoDT = ?, Password = ? WHERE MaBHYT = ?";
	    PreparedStatement ps = kn.cn.prepareStatement(sql);
	    ps.setString(1, hodem);
	    ps.setString(2, tenbn);
	    ps.setString(3, ngaysinh);
	    ps.setString(4, gioitinh);
	    ps.setString(5, DiaChi);
	    ps.setString(6, SoDT);
	    ps.setString(7, Password);
	    ps.setString(8, mabn);
	    ps.executeUpdate();
	    kn.cn.close(); // Close the connection when you are done
	}
}

