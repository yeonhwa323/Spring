package org.doit.ik.di4.test;

import org.doit.ik.di4.RecordViewImpl4;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex05 {
	public static void main(String[] args) {

		String resourceLocations = "classpath:org/doit/ik/di4/application-context4.xml";
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);

		RecordViewImpl4 rvi = ctx.getBean("recordViewImpl4", RecordViewImpl4.class);
		rvi.input();
		rvi.output();

		System.out.println("end");
	}
}
