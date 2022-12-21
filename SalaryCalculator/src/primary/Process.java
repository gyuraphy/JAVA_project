package primary;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class Process {
	// 국민연금, 건강보험, 고용보험, 장기요양보험, 갑근세, 주민세, 세금, 세후
	private Double gukmin, gungang, goyong, janggi, gabgeun, jumin, tax, afterTax;
	private Double pay;
	private int oopay;
	private NumberFormat currency_format = NumberFormat.getCurrencyInstance(Locale.KOREA);
		
	public Process() {}
	
	public void start(double pay, int oopay) {
		this.pay = pay;
		this.oopay = oopay;
		currency_format.setMinimumFractionDigits(0);
	}
	
	public String getGukmin() {
		gukmin = pay * 0.045;
		gukmin = decimalScale(Double.toString(gukmin), 0, 1);
		return currency_format.format(gukmin);
	}
	
	public String getGungang() {
		gungang = pay * 0.03545;
		gungang = decimalScale(Double.toString(gungang), 0, 1);		
		return currency_format.format(gungang);
	}
	
	public String getGoyong() {
		goyong = pay * 0.009;
		goyong = decimalScale(Double.toString(goyong), 0, 1);		
		return currency_format.format(goyong);
	}
	
	public String getJanggi() {
		janggi = gungang * 0.1281;
		janggi = decimalScale(Double.toString(janggi), 0, 1);
		return currency_format.format(janggi);
	}
	
	public String getGabgeun() {		
		Long yearPay = oopay * 12L;
		double gongje = 0;
		double tempPay = 0;
		double midPay = 0;
		double lastPay = 0;
		double atLastPay = 0;
		double bonus = 0;
		
		if(yearPay <= 5000000) {
			gongje = yearPay * 0.8;
		} else if(yearPay > 5000000 && yearPay <= 15000000) {
			gongje = 4000000 + ((yearPay - 5000000) * 0.5);
		} else if(yearPay > 15000000 && yearPay <= 30000000) {
			gongje = 9000000 + ((yearPay - 15000000) * 0.15);
		} else if(yearPay > 30000000 && yearPay <= 45000000) {
			gongje = 11250000 + ((yearPay - 30000000) * 0.1);
		} else if(yearPay > 45000000) {
			gongje = 12750000 + ((yearPay - 45000000) * 0.05);
		}
		
		if((gukmin*12) < 220000)
			bonus = 118800;
		else if((gukmin*12) > 3600000)
			bonus = 1944000;
		else
			bonus = gukmin * 12;
		
		tempPay = yearPay - (gongje + 1500000 + bonus + (1100000 + yearPay * 0.025));
		
		if(tempPay <= 12000000) {
			midPay = tempPay * 0.06;
		} else if(tempPay > 12000000 && tempPay <= 46000000) {
			midPay = 720000 + ((tempPay - 12000000) * 0.16);
		} else if(tempPay > 46000000 && tempPay <= 88000000) {
			midPay = 5820000 + ((tempPay - 46000000) * 0.24);
		} else if(tempPay > 88000000) {
			midPay = 15900000 + ((tempPay - 88000000) * 0.35);
		}
		
		if(midPay <= 500000) {
			lastPay = midPay * 0.55;
		} else if(midPay > 500000) {
			lastPay = 275000 + ((midPay - 500000) * 0.3);
		}
		
		atLastPay = midPay - lastPay;
		gabgeun = atLastPay / 12;
			
		gabgeun = decimalScale(Double.toString(gabgeun), 0, 1);
				
		return currency_format.format(gabgeun);
	}
	
	public String getJumin() {
		jumin = gabgeun * 0.1;
		jumin = decimalScale(Double.toString(jumin), 0, 1);
		return currency_format.format(jumin);
	}
	
	public String getBeforeTax() {
		return currency_format.format(pay);
	}
	
	public String getTax() {
		tax = gukmin + gungang + goyong + janggi + gabgeun + jumin;
		return currency_format.format(tax);
	}
	
	public String getAfterTax() {
		afterTax = pay - tax;
		return currency_format.format(afterTax);
	}
	
	public double decimalScale(String decimal , int loc , int mode) {
		
		BigDecimal bd = new BigDecimal(decimal);
		BigDecimal result = null;
		
		if(mode == 1) {
			result = bd.setScale(loc, BigDecimal.ROUND_DOWN);
		}
		else if(mode == 2) {
			result = bd.setScale(loc, BigDecimal.ROUND_HALF_UP);
		}
		else if(mode == 3) {
			result = bd.setScale(loc, BigDecimal.ROUND_UP);
		}
		
		return result.doubleValue();
	}	
}
