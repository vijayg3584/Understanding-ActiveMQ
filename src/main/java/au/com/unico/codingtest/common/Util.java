package au.com.unico.codingtest.common;

import org.apache.commons.math3.util.ArithmeticUtils;

public class Util {
	
	public static int getGcd(Integer i1, Integer i2) {
		return ArithmeticUtils.gcd(i1, i2);
	}

}