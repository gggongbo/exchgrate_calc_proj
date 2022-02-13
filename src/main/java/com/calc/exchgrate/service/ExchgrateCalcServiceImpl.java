package com.calc.exchgrate.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import com.calc.exchgrate.dto.ExchgrateCalcParamDTO;
import com.calc.exchgrate.dto.ExchgrateCalcReturnDTO;

@Service("ExchgrateCalcService")
@PropertySource("classpath:application.properties")
public class ExchgrateCalcServiceImpl implements ExchgrateCalcService{
	
    @Value("${exchgrate.calc.url}")
    String exchgrateCalcUrl;
    
    @Value("${exchgrate.calc.access.key}")
    String accessKey;
    
    @Value("${exchgrate.calc.send.country}")
    String sendCountry;

	//환율 및 수취금액 계산 서비스
	@Override
	public ExchgrateCalcReturnDTO getExchgRateCalc(ExchgrateCalcParamDTO exchgrateCalcParamDTO) throws Exception{
		DecimalFormat dFormatter = new DecimalFormat("###,###.00");
		int rcvCountryCode = exchgrateCalcParamDTO.getCountryCode();
		String rcvCountryName = "";
		long currencyAmount = exchgrateCalcParamDTO.getCurrencyAmount();
		Double getExchgRate = 0.;
		String getCurrencyAmount = "";
		
		ExchgrateCalcReturnDTO resultDTO = new ExchgrateCalcReturnDTO();
		
		try {
			//수취국가 변환 1-KRW(한국), 2-JPY(일본), 3-PHP(필리핀)
			switch (rcvCountryCode) {
			case 1:
				rcvCountryName = "KRW";
				break;
			case 2:
				rcvCountryName = "JPY";
				break;
			case 3:
				rcvCountryName = "PHP";
				break;
			default:
				break;
			}
			
			//환율 row date 반환
			Double rowExchgRate = getRowExchgRate(sendCountry+rcvCountryName);
			//환율 반환- 소수점 셋째 자리부터 버림 처리
			getExchgRate = Math.floor(rowExchgRate * 100) / 100.0;
			//수취금액 반환- 소수점 셋째 자리부터 버림 처리
			Double rowCurrencyAmount = Math.floor((rowExchgRate * currencyAmount)*100) / 100.0;
			//수취금액+수취국가 형태로 데이터 셋팅
			getCurrencyAmount = dFormatter.format(rowCurrencyAmount)+ " " + rcvCountryName;
			
			//리턴할 DTO값 세팅
			resultDTO.setExchgRate(getExchgRate);
			resultDTO.setGetCurrencyAmount(getCurrencyAmount);
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return resultDTO;
	}
	
	public Double getRowExchgRate(String countryName) throws Exception {
		HttpURLConnection conn = null;
        String connUrl = exchgrateCalcUrl + "?access_key=" + accessKey;
        JSONObject exchgRateJson = null;
        Double rowExchgRate = 0.;
        try {
        	
        	//http url connection Start
            URL url = new URL(connUrl);
            conn = (HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(5000); 	//connection timeout 설정
            conn.setReadTimeout(5000); 		//read timeout 설정
            conn.setRequestMethod("GET"); 	//request method 설정

            int responseCode = conn.getResponseCode(); //통신 응답 코드 반환
            
            //통신 응답코드 확인 Start
            if (responseCode != 200) {
                throw new Exception("error: error occured getting response from api server");
            } 
            else {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                StringBuilder resultSb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                	resultSb.append(line);
                }
                
                //JSON 파싱 Start
                JSONObject resultJson = (JSONObject) new JSONParser().parse(resultSb.toString());
    			exchgRateJson = (JSONObject) resultJson.get("quotes");
    			//JSON 파싱 End
    			
    			//row exchange rate 반환
    			rowExchgRate = (Double)exchgRateJson.get(countryName);
    			
            }
          //통신 응답코드 확인 End
          conn.disconnect();
          //http url connection End
            
        } catch (Exception e) {
        	throw new Exception("error: "+e.getMessage());
        } 
        
		return rowExchgRate;
	}

}
