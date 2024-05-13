package be6_day12.entities;

public class Rank {
public String name;
public float percentDiscountOnTotal;
public float percentDiscountOnShippingFee;

public int upperThreshold;
public int lowerThreshold;

public Rank(String name, float percentDiscountOnTotal,float percentDiscountOnShippingFee, int lowerThreshold, int upperThreshold) {
	this.name = name;
	this.percentDiscountOnTotal = percentDiscountOnTotal;
	this.percentDiscountOnShippingFee = percentDiscountOnShippingFee;
	this.upperThreshold = upperThreshold;
	this.lowerThreshold = lowerThreshold;
	
	
}
}


