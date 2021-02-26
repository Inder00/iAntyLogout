package pl.inder00.antylogout.utils;

import java.text.DecimalFormat;

public class DoubleFormatUtil {
	
	private double var;
	
	public DoubleFormatUtil(double b){	
		this.var = b;
	}
	public String output(){
		
		return new DecimalFormat("#0.00").format(this.var);
		
	}

}
