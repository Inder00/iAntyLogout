package pl.inder00.antylogout.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
public class TimeUtil {
	
	public static long addTime(int seconds){
		return getTime()+seconds*1000L;
	}
	
	public static long getTime(){
		return System.currentTimeMillis();
	}
	
	public static double outTime(long sg){
		
		double f = (double)((long) sg/10L) / (double) 100;
		
		return new BigDecimal(f).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
	}
	
	public static long fromTime(long start){
		return (start-getTime());
	}

}

