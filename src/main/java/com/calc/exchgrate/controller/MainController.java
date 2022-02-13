package com.calc.exchgrate.controller;

import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.calc.exchgrate.dto.ExchgrateCalcParamDTO;
import com.calc.exchgrate.dto.ExchgrateCalcReturnDTO;
import com.calc.exchgrate.service.ExchgrateCalcService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	ExchgrateCalcService exchgrateCalcService;
	
	//root 경로 접속 controller
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Locale locale, Model model) {
		return "index";
	}
	
	//환율계산 controller
	@RequestMapping(value = "/exchgrateCalc.do", method = RequestMethod.POST)
	@ResponseBody
	public ExchgrateCalcReturnDTO exchgrateCalc(ExchgrateCalcParamDTO exchgrateCalcParamDTO) {
		//logger.info(exchgrateCalcParamDTO.toString());
		ExchgrateCalcReturnDTO returnDTO = null;
		
		try {
			returnDTO = exchgrateCalcService.getExchgRateCalc(exchgrateCalcParamDTO);
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return returnDTO;
	}
	
}
