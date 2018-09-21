package com.notinha.util;

import java.text.DecimalFormat;

public class NumeroFormato {

	
	public Double doubleDoisDecimais(Double d) {
		DecimalFormat fmt = new DecimalFormat("0.00");
		String s = fmt.format(d);
		String[] part = s.split("[,]");
		s = part[0]+"."+part[1]; 
		return Double.parseDouble(s);
	}
}
