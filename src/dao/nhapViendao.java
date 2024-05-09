package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.bacSibean;
import bean.nhapVienbean;

public class nhapViendao {
	public ArrayList<nhapVienbean> getNV() throws Exception {
		ArrayList<nhapVienbean>  ds = new ArrayList<nhapVienbean>();
		// b1: Kết nối vào csdl : QlBenhNhan
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		
		// b2: Tạo câu lệnh sql:
		String sql = "select * from LICHSU";
		
		// b3: Tạo câu lệnh
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		
		// b4: Truyền tham số vào câu lệnh nếu có
		
		// b5: Thực hiện câu lệnh
		ResultSet rs = cmd.executeQuery();
		
		// b6: Duyệt rs
		while (rs.next()) {
			String maNhapVien = rs.getString("MaLS");
			String maBn = rs.getString("MaBHYT");
			Date ngayNV = rs.getDate("NgayNV");
			Date ngayXV = rs.getDate("NgayXV");
			String chuanDoan = rs.getString("ChuanDoan");
			String tieuSu = rs.getString("TieuSu");
			
			ds.add(new nhapVienbean(maBn, ngayNV, ngayXV, chuanDoan, tieuSu, maNhapVien));
		}
		
		// b7: Đóng các đối tượng
		rs.close();
		kn.cn.close();
		
		return ds;
	}
	
	public void addNhapVien(String maBn, String ngayNV, String ngayXV, String chuanDoan, String tieuSu) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "INSERT INTO LICHSU (MaBHYT, NgayNV, NgayXV, ChuanDoan, TieuSu) VALUES(?,?,?,?,?)";
	    PreparedStatement ps = kn.cn.prepareStatement(sql);
	    ps.setString(1, maBn);
	    ps.setString(2, ngayNV);
	    ps.setString(3, ngayXV);
	    ps.setString(4, chuanDoan);
	    ps.setString(5, tieuSu);
	    ps.executeUpdate();
	    kn.cn.close(); // Close the connection when you are done
	}
	
	public void XoaNhapVien(String maNhapVien) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "delete FROM LICHSU where MaLS = ?";
		PreparedStatement ps = kn.cn.prepareStatement(sql);
		ps.setString(1,maNhapVien);
		ps.executeUpdate();
		kn.cn.close();
	}
	
	public nhapVienbean getInfoNV(String maNhapVien) throws Exception {
	    // Bước 1: Kết nối vào cơ sở dữ liệu: QlBenNhan
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();

	    // Bước 2: Tạo câu lệnh SQL
	    String sql = "select * from LICHSU WHERE MaLS = ?";

	    // Bước 3: Tạo câu lệnh
	    PreparedStatement ps = kn.cn.prepareStatement(sql);

	    // Bước 4: Truyền tham số vào câu lệnh nếu có
	    ps.setString(1, maNhapVien);

	    // Bước 5: Thực hiện câu lệnh
	    ResultSet rs = ps.executeQuery();

	    // Bước 6: Xử lý kết quả
	    if (rs.next()) {
	        nhapVienbean nv = new nhapVienbean();
	        nv.setMaNhapVien(rs.getString("MaLS"));
	        nv.setMaBN(rs.getString("MaBHYT"));
	        nv.setNgayNV(rs.getDate("NgayNV"));
	        nv.setNgayXV(rs.getDate("NgayXV"));
	        nv.setChuanDoan(rs.getString("ChuanDoan"));
	        nv.setTieuSu(rs.getString("TieuSu"));

	        // Đóng kết nối và trả về đối tượng bacSibean
	        kn.cn.close();
	        return nv;
	    } else {
	        // Nếu không có dữ liệu, đóng kết nối và trả về null
	        kn.cn.close();
	        return null;
	    }
	}
	
	public void SuaNV(String maBn, String ngayVao, String ngayRa, String chuanDoan, String tieuSu, String maNhapVien) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "UPDATE LICHSU SET MaBHYT=?, NgayNV =?, NgayXV = ?, ChuanDoan =? , TieuSu = ? WHERE MaLS = ?";
	    PreparedStatement ps = kn.cn.prepareStatement(sql);
	    ps.setString(1, maBn);
	    ps.setString(2, ngayVao);
	    ps.setString(3, ngayRa);
	    ps.setString(4, chuanDoan);
	    ps.setString(5, tieuSu);
	    ps.setString(6, maNhapVien);
	    ps.executeUpdate();
	    kn.cn.close(); // Close the connection when you are done
	}
}
