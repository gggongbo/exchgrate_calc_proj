package com.calc.exchgrate.service;

import org.springframework.stereotype.Service;
import com.calc.exchgrate.dto.ExchgrateCalcParamDTO;
import com.calc.exchgrate.dto.ExchgrateCalcReturnDTO;

@Service
public interface ExchgrateCalcService {
	
	//환율 및 수취금액 계산 서비스
	public ExchgrateCalcReturnDTO getExchgRateCalc(ExchgrateCalcParamDTO exchgrateCalcParamDTO) throws Exception;

}
