package org.doit.ik;

import java.util.List;

import org.doit.ik.domain.DeptEmpSalgradeDTO;
import org.doit.ik.domain.EmpDTO;
import org.doit.ik.mapper.DeptEmpSalgradeMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
public class DeptEmpMybatisController {
	
	//@Autowired
	private DeptEmpSalgradeMapper deptEmpSalgrademapper;
	
	// 컨트롤러 메서드 선언
	@GetMapping("/dept/emp")
	private void getDeptEmpSalgrade(Model model) {
		log.info("> DeptEmpSalgradeMapper.getDeptEmpSalgrade()...."); 
		List<DeptEmpSalgradeDTO> desList = this.deptEmpSalgrademapper.getDept();
		
		for (DeptEmpSalgradeDTO deptEmpSalgradeDTO : desList) {
			List<EmpDTO> empList = this.deptEmpSalgrademapper.getEmpOfDept(deptEmpSalgradeDTO.getDeptno());
			
			deptEmpSalgradeDTO.setEmpList(empList);			
		}
		
		model.addAttribute("desList", desList);
	}
	
}
