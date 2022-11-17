<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   <script>
  'use strict';
  let cnt = 1;
 
  $(document).ready (function(){
      $('#btnAdd').click (function(){
          cnt++;
         $('#addList').append (
            '<br/>상품명 : <input type="text" name="product" id="product'+cnt+'" style="width:100px; height:20px;" />&nbsp;&nbsp;'+
            '가격 : <input type="number" name="price" id="price'+cnt+'" style="width:100px; height:20px;" />&nbsp;&nbsp;&nbsp;'+
            '수량 : <input type="number" name="su" id="su'+cnt+'" style="width:100px; height:20px;" />&nbsp;&nbsp;'+
            '     '+'<input type="button" id="btnRemove" value="삭제" class="btn btn-outline-info"><br/>'
            );
          $('.btnRemove').on('click', function(){
              $(this).prev().remove();
              $(this).next().remove();
              $(this).remove();
          });
      });
  });
  
     function fCheck() {
      let product1 = document.getElementById("product1").value;
      
      if(product1 == "") {
         alert("첫번째 상품은 꼭 등록하셔야 주문이 완료됩니다.");
         document.getElementById("product1").focus();
      }
      else {
        myform.submit();
      }
     } 
    
    </script>
<h2><b>상 품 등 록</b></h2>
<br/>
<hr/>
<form name="myform" method="post" action="<%=request.getContextPath()%>/j1116h/Hw1115">
    <p>구매자 : <input type="text" name="guest" id="guest" autofocus required/></p>
    <div>
      상품분류 : <select name="classification" id="classification">
      <option>커피</option>
      <option>스무디</option>
      <option>차</option>
      <option>디저트</option>
      </select>
    </div>
    <br/><br/>
  <input type="button" value="상품추가" id="btnAdd" class="form-control"/>
  <br/>
  <div id="addList">
      상품명 : <input type="text" name="product" id="product1" style="width:100px; height:20px;" />
      가격 : <input type="number" name="price" id="price1" style="width:100px; height:20px;" />
      수량 : <input type="number" name="su" id="su1" style="width:100px; height:20px;" /><br/>
  </div>        
    <br/>
    <p>
      <input type="button" value="확인" onclick="fCheck()" class="btn btn-outline-danger"/>
      <input type="reset" value="다시입력" class="btn btn-outline-warning"/>
    </p>
  </form>
