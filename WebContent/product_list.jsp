<%@page import="mybatis.vo.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="menu.jsp"/>
<%
	//요청시 한글처리
	request.setCharacterEncoding("utf-8");
%>
	<jsp:useBean id="sb" class="shop.bean.ShopBean" scope="session"/>
	<%--현재 페이지로 넘어올 때 category라는 파리미터가 넘어온다.
		이 값을 ShopBean에 있는 category에 저장하자!
	 --%>
	<jsp:setProperty name="sb" property="category"/>
	<% 
		sb.searchProduct(); // 사용자가 선택한 카테고리별 제품들 검색이 되었다.
							// ShopBean(안에 p_list)
		ProductVO[] ar = sb.getP_list();
		
		
	%>
	<table align="center" width="600" border="1" style="border-collapse:collapse; font-size: 11px" bordercolor="navy" cellpadding="4" cellspacing="0">
		<colgroup>
			<col width="10%"/>
			<col width="10%"/>
			<col width="35%"/>
			<col width="20%"/>
			<col width="25%"/>
		</colgroup>
		<thead>
			<tr>
				<th>제품번호</th>
				<th>이미지</th>
				<th>제품명</th>
				<th>제품가격</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody>
<%
		if(ar != null){
			for(ProductVO pvo : ar){
%>
		<tr align="center">
			<td><%=pvo.getP_num() %></td>
			<td><img src="images/<%=pvo.getP_image_s()%>" width="60px" height="50px"/></td>
			<td>
				<a href="product_content.jsp?prod_num=<%=pvo.getP_num() %>">
				<%=pvo.getP_name() %>
				</a>
			</td>
			<td>
				할인가: <%=pvo.getP_saleprice() %>원<br/>
				<font color="red">(<%=pvo.getPercent()%>%)</font>
			</td>
			<td>
				단가:<%=pvo.getP_price() %>원
			</td>
		</tr>
<%
			}// for문의 끝
		}//if문의끝
%>
		</tbody>
	</table>
</body>
</html>