package org.doit.ik.domain;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 12:02 수업시작~ 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpDTO {

	private int empno;
	private String ename;
	private String job;
	@JsonIgnore  // JSON결과에 제외
	private int mgr;
	@JsonFormat(pattern = "yyyyMMddHHmmss" ) /// 날짜 형식 변환
	private Date hiredate;
	private int sal;
	private double comm;
	private int deptno;

}
