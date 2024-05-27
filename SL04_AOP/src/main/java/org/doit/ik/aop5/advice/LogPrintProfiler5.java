package org.doit.ik.aop5.advice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component("logPrintProfiler5")
@Aspect
public class LogPrintProfiler5 {
	
	//<aop:pointcut expression="execution(* org.doit.ik.aop..*.*(*,*))" id="calPointcut"/>
	@Pointcut(value = "execution(* org.doit.ik.aop..*.*(*,*))")
	private void calPointcut() {
		
	}
	
	// 3. AfterAdvice p221
	// <aop:around method="trace" pointcut-ref="calPointcut" />
	@After("calPointcut()")
	public void afterFinally(JoinPoint joinPoint) {
		
		String methodName = joinPoint.getSignature().getName();
		Log log = LogFactory.getLog(this.getClass());
		log.info("<<< " + methodName + "() : LogPrintProfiler4.afterFinally 가 호출됨... ");
	}

	// 2. BeforeAdvice p217
	@Before("calPointcut()")
	public void before(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		Log log = LogFactory.getLog(this.getClass());
		log.info(">>> " + methodName + "() : LogPrintProfiler4.before 가 호출됨... ");
	}

	// 1. AroundAdvice  p222
	@Around("calPointcut()")
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable{

		String methodName = joinPoint.getSignature().toShortString();

		Log log =  LogFactory.getLog(this.getClass());

		log.info("> " + methodName +"() start.");

		StopWatch sw = new StopWatch();
		sw.start();       

		// 핵심 관심 사항.
		Object  result = joinPoint.proceed() ;  // calc.add()     

		sw.stop();

		log.info("> " + methodName +"() end.");
		log.info("> " + methodName +"() 처리 시간 :  " + sw.getTotalTimeMillis() +"ms");

		return result;

	}
}
