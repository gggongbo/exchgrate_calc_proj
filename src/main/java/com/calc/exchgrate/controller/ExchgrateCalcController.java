package com.calc.exchgrate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping(value = "/exchgrateCalc")
public class ExchgrateCalcController {
	
	private static final Logger logger = LoggerFactory.getLogger(ExchgrateCalcController.class);
	
	@Autowired
	ExchgrateCalcService exchgrateCalcService;
	
	//환율계산 controller
	@RequestMapping(value = "/get", method = RequestMethod.GET)
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
