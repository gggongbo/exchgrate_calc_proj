<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Exchgrate Calculator</title>
	<style type="text/css">
	.table {
	text-align : left;
	}
	</style>
</head>
<body>
<div class="content">
	<div class="content-header">
		<h1 class="content-title">환율 계산</h1>
	</div>
	<!-- /.content-header -->
	
	<form role="form">
		<div class="content-body">
			<table class="table table-unbordered">
				<tr>
				<th>송금국가:</th>
				<td>미국(USD)</td>
				</tr>
				<tr>
				<th>수취국가:</th>
				<td><select name="countryCode">
					<option value="1">한국</option>
					<option value="2">일본</option>
					<option value="3">필리핀</option>
				</select></td>
				</tr>
				<tr>
				<th>환율:</th>
				<td><input name="exchgrate" type="text" style="border:none" readonly></td>
				</tr>
				<tr>
				<th>송금액:</th>
				<td><input name="currencyAmount" type="number"></td>
				</tr>
			</table>
		</div>
		<!-- /.content-body -->
	</form>
	
	<div class="content-footer">
		<button type="submit" class="btn btn-submit">submit</button>
		<br><br>
		<input name="getCurrencyAmount" style="border:none;width:400px;" type="text"readonly>
	</div>
	<!-- /.content-footer -->
	
</div>
<!-- /.content -->
</body>
<script type="text/javascript" src="//code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	$(document).ready(function() {
		
		//button submit Start
		$(".btn-submit").on("click", function() {
			$("input[name='exchgrate']").empty();
			$("input[name='getCurrencyAmount']").empty();
			var formData =$("form[role='form']").serialize();
			
			//파라미터 유효성 체크
			if(!validationCheck()){
				return;
			}
			
			//환율 계산 Start
			$.ajax({
				  url: "/exchgrateCalc/get",
				  data: formData,
				  type: 'GET',
				  async: false, 
				  dataType: 'json',
				  success: function(data){      
					  var exchgRate = data.exchgRate;
					  var getCurrencyAmount = "수취금액은 " +data.getCurrencyAmount + "입니다.";
					  
		              $("input[name='exchgrate']").val(exchgRate);
		              $("input[name='getCurrencyAmount']").val(getCurrencyAmount);
		            },	
		            error: function(){	
		                alert("AJAX error");	
		            }
			});
			//환율 계산 End
		});
		//button submit End
		
		//파라미터 유효성 체크 Start
		function validationCheck() {
			var chkValue = true;
			var chkIsNumber = /[0-9]/;
			var currencyAmount = $("input[name='currencyAmount']").val();
			var countryCode = $("select[name='countryCode']").val();
			var currencyAmountMsg = "송금액이 바르지 않습니다";
			var countryCodeMsg = "수취국가가 바르지 않습니다";
			
			//송금액 null 빈값 체크
			if(currencyAmount==null||currencyAmount==""){
				alert(currencyAmountMsg);
				chkValue = false;
			}
			//송금액 범위 체크
			if(currencyAmount<0||currencyAmount>10000){
				alert(currencyAmountMsg);
				chkValue = false;
			}
			//송금액이 숫자인지 체크
			if(!chkIsNumber.test(currencyAmount)){
				alert(currencyAmountMsg);
				chkValue = false;
			}
			
			//수취국가 null 빈값 체크
			if(countryCode==null||countryCode==""){
				alert(countryCodeMsg);
				chkValue = false;
			}
			//수취국가 범위 체크
			if(countryCode<1||countryCode>3){
				alert(countryCodeMsg);
				chkValue = false;
			}
			
			return chkValue;
		}
		//파라미터 유효성 체크 End
		


	});
	
</script>
</html>
