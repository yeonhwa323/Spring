package org.doit.ik;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.tiles.request.Request;
import org.doit.ik.mapper.TimeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TimeMybatisController {
	
	private static final Logger logger = LoggerFactory.getLogger(TimeMybatisController.class);
	
	@Autowired(required = false) // ""쓰지말기!!
	private TimeMapper timeMapper;
	
	//@RequestMapping(value = "/time", method = RequestMethod.GET)
	@GetMapping(value = "/time")
	public String home(Locale locale, Model model
			, HttpServletRequest request ) {
		logger.info("> TimeMybatisController.time()... ");
		
		String currentTime = this.timeMapper.getTime();
		
		model.addAttribute("currentTime", currentTime );
		request.setAttribute("currentTime2", currentTime );
		
		String currentNextTime = this.timeMapper.getNextTime();
		model.addAttribute("currentNextTime", currentNextTime );
		
		return "time"; // /WEB-INF/views/time.jsp
		
	}
	
}
