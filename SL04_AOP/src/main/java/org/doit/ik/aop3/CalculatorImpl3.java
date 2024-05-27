package org.doit.ik.aop3;

import org.doit.ik.aop.Calculator;
import org.springframework.stereotype.Component;

@Component("calc") // 자동으로 스캔대상으로 설정 - 객체생성 첫글자 소문자로 설정되는 거 주의!
public class CalculatorImpl3 implements Calculator {

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
