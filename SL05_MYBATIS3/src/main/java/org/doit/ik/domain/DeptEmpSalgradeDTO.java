package org.doit.ik.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptEmpSalgradeDTO {
	
	// 1:1 관계
	private DeptDTO deptDTO;
	
	// 1:N 관계
	private EmpDTO empDTO ; // List<EmpDTO> empDTOList;
}
