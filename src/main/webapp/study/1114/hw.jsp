<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>hw.jsp</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script>
	'use strict';
		
	let addTbl;

  function insRow() {
    addTbl = document.getElementById("addTable");
    let addRow = addTbl.insertRow();   // addTbl테이블의 행의 개념으로 한개를 추가....의 의미
    addRow.onmouseover = function() {   // clickedRowIndex : 클릭한 Row의 위치를 반환(확인)
      addTbl.clickedRowIndex = this.rowIndex;
    }
    let addCell = addRow.insertCell();  // 앞에서 클릭된 행의 위치를 얻어와서, 현재 테이블 해당행의 열(셀)로 삽입한다.

    // 앞에서 삽입시켜놓은 셀에 추가될 테이블의 내용을 기록해 준다.
    let formTag = "";
    formTag += '상품명 :<input type="text" name="product" style="width:60px; height:20px; "/>';
    formTag += '가격 :<input type="text" name="result" readonly style="width:60px; height:20px;"/>';
    // formTag += '수량:<input type="text" name="addText2" style="width:60px; height:20px; "/>';
    formTag += '수량 :<input type="text" name="su" style="width:60px; height:20px; " onblur="formCalc();"/>';
    formTag += '<input type="button" value="삭제" onclick="removeRow()"/>';
    
    addCell.innerHTML = formTag;
  }

  // 추가된 행(table)을 삭제
  function removeRow() {
    addTable.deleteRow(addTbl.clickedRowIndex);
  }

  function fChcek() {
    var myform = document.myform;

    // 추가된 상품박스란에 입력된 항목을 검사하는 루틴....
    for(var i=0; i<myform.elements.length; i++) {
      if(myform.elements[i].name == "addText1") {
        if(myform.elements[i].value == "") {
          alert("텍스트 박스안에 상품명을 입력하세요.");
          myform.elements[i].focus();
          return false;
        }
      }
      else if(myform.elements[i].name == "addText2") {
        if(myform.elements[i].value == "") {
          alert("텍스트 박스안에 상품명을 입력하세요.");
          myform.elements[i].focus();
          return false;
        }
      }
    }

    // 입력박스안의 내용이 모두 입력되었으면 처리하는 부분
    var result = "";
    for(var i=0; i<myform.elements.length; i++) {
      if(myform.elements[i].name == "addText1") {
        result += myform.elements[i].value + "*";
      }
      else if(myform.elements[i].name == "addText2") {
        result += myform.elements[i].value + "=";
        myform.elements[i+1].value = parseInt(myform.elements[i].value) * parseInt(myform.elements[i-1].value);
        result += myform.elements[i+1].value + "\n";
      }
    }
    result = result.substring(0,result.length-1);
    alert("입력창에 입력된 상품명은? \n" + result);
  }

  function formCalc() {
    // document.myform.result.value = parseInt(myform.addText1.value) * parseInt(myform.addText2.value);
    // 삽입된 항목으로 다시 재계산을 호출하기에 아래 for문에서 입력된 폼을 모두 다시 체크해서 재계산하고 있다..
    for(var i=1; i<myform.elements.length; i++) {   // 텍스트필드는 1번째 인덱스부터이다.
      if(myform.elements[i].name == "product") {
        // 단가텍스트필드는 1(i), 수량텍스트필드는 2(i+1), 금액 텍스트필드는 3(i+2)
        myform.elements[i+2].value = parseInt(myform.elements[i].value) * parseInt(myform.elements[i+1].value);
      }
    }
  }
		/* function fCheck() {
			let product1 = document.getElementById("product1").value;
			
			if(product1 == "") {
				alert("첫번째 상품은 꼭 등록하셔야 주문이 완료됩니다.");
				document.getElementById("product1").focus();
			}
			else {
				myform.submit();
			}
		} */
	</script>
</head>
<body>
<p><br/></p>
<div class="container">
<form name="myform" method="post" action="<%=request.getContextPath()%>/j1114_HwOk">
<table>
  <tr>
    <td>
    	구매자 : <input type="text" name="guest" required/><br/><br/>
    	상품분류 : 
    	<select name="" id="">
    	<option>커피</option>
    	<option>스무디</option>
    	<option>차</option>
    	<option>디저트</option>
    	</select>
    	<br/><br/>
      <input type="button" value="상품추가" onclick="insRow()" class="form-control"/>
      <br/>
    </td>
  </tr>
  <tr>
    <td>
      <table id="addTable" width="400px">
        <tr>
          <td>
            상품명 : <input type="text" name="product" id="product" required style="width:60px; height:20px; "/>
          </td>
          <td>
            가격 : <input type="text" name="result" id="result" required style="width:60px; height:20px;"/>
          </td>
          <td>
            수량 : <input type="text" name="su" id="su" required style="width:60px; height:20px; " onblur="formCalc();"/>
          </td>
          <tr>
	          <td>
	            상품명 : <input type="text" name="product" style="width:60px; height:20px; "/>
	          </td>
	          <td>
	            가격 : <input type="text" name="result" readonly style="width:60px; height:20px;"/>
	          </td>
	          <td>
	            수량 : <input type="text" name="su" style="width:60px; height:20px; " onblur="formCalc();"/>
	          </td>
          </tr>
          <tr>
	          <td>
	            상품명 : <input type="text" name="product" style="width:60px; height:20px; "/>
	          </td>
	          <td>
	            가격 : <input type="text" name="result" readonly style="width:60px; height:20px;"/>
	          </td>
	          <td>
	            수량 : <input type="text" name="su" style="width:60px; height:20px; " onblur="formCalc();"/>
	          </td>
          </tr>
          <tr>
	          <td>
	            상품명 : <input type="text" name="product" style="width:60px; height:20px; "/>
	          </td>
	          <td>
	            가격 : <input type="text" name="result" readonly style="width:60px; height:20px;"/>
	          </td>
	          <td>
	            수량 : <input type="text" name="su" style="width:60px; height:20px; " onblur="formCalc();"/>
	          </td>
          </tr>
      </table>
    </td>
  </tr>
</table>
<br/>
<table width="400px">
  <tr>
    <td style="text-align:center">
      <input type="button" value="확인" onclick="fCheck()"/>
      <input type="reset" value="다시입력"/>
      <input type="button" value="초기화" onclick="location.reload()"/>
    </td>
  </tr>
</table>
	<%-- <h2>상 품 등 록</h2>
	<form name="myform" method="post" action="<%=request.getContextPath()%>/j1114_Test3Ok">
		<p>상품1 : <input type="text" name="product" id="product1" autofocus /></p>
		<p>상품2 : <input type="text" name="product" id="product2"/></p>
		<p>상품3 : <input type="text" name="product" id="product3"/></p>
		<p>상품4 : <input type="text" name="product" id="product4"/></p>
		<p>상품5 : <input type="text" name="product" id="product5"/></p>
		<p>상품6 : <input type="text" name="product" id="product6"/></p>
		<p>상품7 : <input type="text" name="product" id="product7"/></p>
		<p>상품8 : <input type="text" name="product" id="product8"/></p>
		<p>
			<input type="button" value="전송" onclick="fCheck()" />
			<input type="reset" value="취소" />
		</p> --%>
	</form>
  
</div>
<p><br/></p>
</body>
</html>