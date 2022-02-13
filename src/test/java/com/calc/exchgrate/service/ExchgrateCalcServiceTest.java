package com.calc.exchgrate.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.calc.exchgrate.dto.ExchgrateCalcParamDTO;
import com.calc.exchgrate.dto.ExchgrateCalcReturnDTO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@PropertySource("classpath:application.properties")
@Log4j
public class ExchgrateCalcServiceTest {
	
	ExchgrateCalcService exchgrateCalcService;
	ExchgrateCalcParamDTO paramDTO;
	ExchgrateCalcReturnDTO returnDTO;
	
	
	@Before
	public void setUp() {
		exchgrateCalcService = new ExchgrateCalcServiceImpl();
		paramDTO = new ExchgrateCalcParamDTO();
		returnDTO = new ExchgrateCalcReturnDTO();
	}
	
	//객체 생성 테스트
	@Test
	public void instanceTest() {
		assertNotNull(exchgrateCalcService);
		assertNotNull(paramDTO);
		assertNotNull(returnDTO);
	}
	
	//환율계산 테스트
	@Test
	public void getExchgRateCalcTest() throws Exception {
		paramDTO.setCountryCode(1);
		paramDTO.setCurrencyAmount(100);
		returnDTO = exchgrateCalcService.getExchgRateCalc(paramDTO);
		assertNotNull(returnDTO);
		log.info(returnDTO.toString());
	}

}
