package org.doit.ik.aop4;

import org.doit.ik.aop.Calculator;
import org.springframework.stereotype.Component;

@Component("calc4") 
public class CalculatorImpl4 implements Calculator {

	@Override
	public int add(int x, int y) {			
		int result = x + y; // 핵심 기능			
		
		return result;
	}

	@Override
	public int sub(int x, int y) {			
		int result = x - y; // 핵심 기능				
		
		return result;
	}

	@Override
	public int mult(int x, int y) {					
		int result = x * y; // 핵심 기능			
		
		return result;
	}

	@Override
	public int div(int x, int y) {				
		int result = x / y; // 핵심 기능		
	
		return result;
	}
	
}
