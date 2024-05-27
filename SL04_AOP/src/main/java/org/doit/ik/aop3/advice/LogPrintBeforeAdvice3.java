package org.doit.ik.aop3.advice;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

@Component
public class LogPrintBeforeAdvice3 implements MethodBeforeAdvice {

	@Override
	public void before(
		Method method  // add()
		, Object[] args // 3, 5 매개변수
		, Object target 
		) throws Throwable {
			String methodName = method.getName();
			Log log = LogFactory.getLog(this.getClass());
			// 인증 처리 체크
			log.info(">>" + methodName + "() : LogPrintBeforeAdvice 호출됨...");
			
		}
		
	}
