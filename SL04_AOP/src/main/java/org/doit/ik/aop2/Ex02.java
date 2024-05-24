package org.doit.ik.aop2;

import org.doit.ik.aop.Calculator;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex02 {
	
	public static void main(String[] args) {
		// application-context2.xml
		// [스프링 3가지 AOP 구현 방법]
		// 1) 스프링 API를 이용해서 AOP 구현
		// org.doit.ik.aop2.advice 패키지
		//	ㄴ LogPrintAroundAdvice.java
		/*
		Calculator calc = new CalculatorImpl2();
		System.out.println( calc.add(3, 5));
		*/
		
		GenericXmlApplicationContext ctx = 
				new GenericXmlApplicationContext("classpath:org/doit/ik/aop2/application-context2.xml");
		
		Calculator calc =  ctx.getBean("calcProxy", Calculator.class);
		System.out.println( calc.add(3, 5));
		
		System.out.println(" end ");
		
	}
	
}
