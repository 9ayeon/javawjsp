package j1116h_gayeon;

import java.util.Arrays;

public class Hw1115VO {
	private String guest;
	private String classI;
	private String[] products;
	private String[] strPrice;
	private String[] strSu;
	private String strProduct;
	private String strProducts;
	
	
	public Hw1115VO(String guest, String classI, String[] products, String[] strPrice, String[] strSu, String strProduct,
			String strProducts) {
		super();
		this.guest = guest;
		this.classI = classI;
		this.products = products;
		this.strPrice = strPrice;
		this.strSu = strSu;
		this.strProduct = strProduct;
		this.strProducts = strProducts;
	}
	public String getGuest() {
		return guest;
	}
	public void setGuest(String guest) {
		this.guest = guest;
	}
	public String getClassI() {
		return classI;
	}
	public void setClassI(String classI) {
		this.classI = classI;
	}
	public String[] getProducts() {
		return products;
	}
	public void setProducts(String[] products) {
		this.products = products;
	}
	public String[] getStrPrice() {
		return strPrice;
	}
	public void setStrPrice(String[] strPrice) {
		this.strPrice = strPrice;
	}
	public String[] getStrSu() {
		return strSu;
	}
	public void setStrSu(String[] strSu) {
		this.strSu = strSu;
	}
	public String getStrProduct() {
		return strProduct;
	}
	public void setStrProduct(String strProduct) {
		this.strProduct = strProduct;
	}
	public String getStrProducts() {
		return strProducts;
	}
	public void setStrProducts(String strProducts) {
		this.strProducts = strProducts;
	}
	@Override
	public String toString() {
		return "Hw1115VO [guest=" + guest + ", classI=" + classI + ", products=" + Arrays.toString(products) + ", strPrice="
				+ Arrays.toString(strPrice) + ", strSu=" + Arrays.toString(strSu) + ", strProduct=" + strProduct
				+ ", strProducts=" + strProducts + "]";
	}

	
}