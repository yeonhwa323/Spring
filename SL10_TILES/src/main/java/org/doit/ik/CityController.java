package org.doit.ik;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/city/*")
public class CityController {

   @GetMapping("/london")
   public String london() {       
      log.info("> CityController.londong GET...");
      return "city/london.tiles";
   }

   @GetMapping("/paris")
   public String Paris() {
      log.info("> CityController.paris GET...");
      return "city/paris.tiles";
   }


   @GetMapping("/seoul")
   public String seoul() {
      log.info("> CityController.seoul GET...");
      return "city/seoul.tiles";
   }

}