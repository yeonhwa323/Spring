package org.doit.ik;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.doit.ik.domain.DeptDTO;
import org.doit.ik.mapper.scott.DeptMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

// Ajax 처리하는 컨트롤러
@RestController
@Log4j
@RequestMapping("/scott/dept/*") 
public class ScottRestController {	
	//private static final Logger logger = LoggerFactory.getLogger(ScottRestController.class); == @Log4j
	
	@Autowired
	private DeptMapper deptMapper;
	
	//@RequestMapping(value = "/scott/dept/new", method = RequestMethod.POST)
	@PostMapping("/new")
	public ResponseEntity<String> insertDept(@RequestBody DeptDTO dto) { // json -> DTO 로 자동변환
		log.info("> ScottRestController.insertDept()...");
		int insertResult = this.deptMapper.insertDept(dto);
		
		return insertResult == 1
				? new ResponseEntity<>("SUCCESS", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
					;
	}
	
	// localhost/scott/dept/50 + delete 요청
	// localhost/scott/dept/50 + GET 요청  scott/dept?deptno=50
	@DeleteMapping(value = "/{deptno}")
	public ResponseEntity<String> deleteDept(@PathVariable("deptno") int deptno){
		log.info("> ScottRestController.insertDept()..." + deptno);
		int deleteResult = this.deptMapper.deleteDept(deptno);
		
		return deleteResult == 1
				? new ResponseEntity<>("SUCCESS", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
					;
	}
	
}
