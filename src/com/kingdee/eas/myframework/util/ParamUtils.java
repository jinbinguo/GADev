package com.kingdee.eas.myframework.util;

import java.io.Serializable;
import java.math.BigDecimal;

public class ParamUtils implements Serializable{
	
	public static void main(String[] args) {
		BigDecimal a = new BigDecimal(11.1206);
		a = a.setScale(2, BigDecimal.ROUND_DOWN);
		System.out.println(a.doubleValue());
	}
}
