package be6_day12.entities;

public class Voucher  {
public double voucherValue;
public String voucherCode;
public int productID;
public Voucher( String voucherCode,int productID, double voucherValue) {
	this.voucherCode = voucherCode;
	this.productID = productID;
	this.voucherValue = voucherValue;
}

}
