package bean;

public class bacSibean {
	private String maBacSi;
	private String tenBacSi;
	private String Nganh;
	private String anhBacSi;
	public bacSibean(String maBacSi, String tenBacSi, String nganh, String anhBacSi) {
		super();
		this.maBacSi = maBacSi;
		this.tenBacSi = tenBacSi;
		Nganh = nganh;
		this.anhBacSi = anhBacSi;
	}
	public bacSibean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMaBacSi() {
		return maBacSi;
	}
	public void setMaBacSi(String maBacSi) {
		this.maBacSi = maBacSi;
	}
	public String getTenBacSi() {
		return tenBacSi;
	}
	public void setTenBacSi(String tenBacSi) {
		this.tenBacSi = tenBacSi;
	}
	public String getNganh() {
		return Nganh;
	}
	public void setNganh(String nganh) {
		Nganh = nganh;
	}
	public String getAnhBacSi() {
		return anhBacSi;
	}
	public void setAnhBacSi(String anhBacSi) {
		this.anhBacSi = anhBacSi;
	}
	
}
