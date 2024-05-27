package org.doit.ik;

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
		log.info("> DeptEmpSalgrademapper.getDeptEmpSalgrade()....");
		model.addAttribute("list", this.deptEmpSalgrademapper.getDeptEmpInfo() );
	}
	
}
