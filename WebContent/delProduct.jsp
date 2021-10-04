<%@page import="shop.bean.Cart"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="cart" class="shop.bean.Cart" scope="session"/>
<%
	// 장바구니에 제품을 삭제를 해보자!!
	
	// 요청시 한글처리
	request.setCharacterEncoding("utf-8");

	// 삭제를 할려면 먼제 넘어온 제품번호가 필요하다! 값을 받아보자
	String del = request.getParameter("p_num");
	
	// 이제 정의한 함수를 불러 장바구니에서 삭제해보자!
	if(cart.delProduct(del)){
	response.sendRedirect("cartList.jsp");
	}else
%>
	<h2>삭제 실패했습니다</h2>
	<a href="index.jsp">메인페이지로 이동</a>
<%
%>
