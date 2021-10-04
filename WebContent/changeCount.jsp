<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%-- 먼저 앞전에 사용된 정보들을 가져올려면 useBean을 사용해야 한다. --%>
    <jsp:useBean id="cart" class="shop.bean.Cart" scope="session"/>
<%
	// 여기는 장바구니의 갯수가 변동이 있을때 확인하는 폼 이다.
	// 먼저 요청시 한글처리
	request.setCharacterEncoding("utf-8");
	
	// 파라미터들 받기
	String pnum = request.getParameter("p_num");
	String q = request.getParameter("count");
	
	int quant = Integer.parseInt(q);
	// 이제수정하는 함수를 불러오자 !! 그전에 만약 수정할려는 갯수가 0 이하면 삭제하는 기능을 추가하자.
	if( quant <= 0){
		response.sendRedirect("delProduct.jsp?p_num="+pnum);
	}else{
		// 여기는 수정할려는 숫자가 1이상일때 실행하는곳이다.
		cart.changeCount(pnum, quant);
		response.sendRedirect("cartList.jsp");
	}
%>