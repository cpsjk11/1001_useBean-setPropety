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
	<!-- 전 페이지에서 제품번호를 담아와 보냈기 때문에 그 값도 받아야 하고 session으로 useBean을 지정해 사용자가 나가기전까지 session내용들은 유지가 된다!
			그러니까 session을 통해 값들을 가져오고 제품번호의 값을 jsp:setProperty를 통해 값을 저장하자!
	 -->
	<jsp:useBean id="sb" class="shop.bean.ShopBean" scope="session"/><!-- scope의 속성을 session으로 지정했기 때문에 다른 페이지를 넘어가도 안에 있는 내용은 사라지지않는다. -->
	<jsp:setProperty name="sb" property="p_num" param="prod_num" /><!-- 이렇게 멤버변수와 파라미터의 이름이 다르면 각각 따로 지정해서 넣어줘야 한다. -->
	<table align="center" width="600" border="1" style="border-collapse:collapse; font-size: 11px" bordercolor="navy" cellpadding="4" cellspacing="0">
		   	<colgroup>
		   		<col width="40%">
		   		<col width="60%">
		   	</colgroup>
			<tbody>
<%
				ProductVO pvo = sb.getProduct();
				if(pvo != null){
%>
			    <tr>
			        <td>제품Category</td>
			        <td><%=pvo.getCategory() %></td>
			    </tr>
			    <tr>
			        <td>제품번호</td>
			        <td><%=pvo.getP_num() %></td>
			    </tr>
			    <tr>
			        <td>제품이름</td>
			        <td><%=pvo.getP_name() %></td>
			    </tr>
			    <tr>
			        <td>제품 판매사</td>
			        <td><%=pvo.getP_company() %></td>
			    </tr>
			    <tr>
			        <td>제품가격</td>
			        <td>(할인가 : <%=pvo.getP_saleprice() %>원)</td>
			    </tr>
			    <tr>
			        <td colspan="2">제품설명</td>
			    </tr>
			    <tr>
			        <td colspan="2" align="center"><img src ="images/<%=pvo.getP_image_l() %>"></td>
			    </tr>
			    <tr>
			        <td colspan="2"><%=pvo.getP_content() %></td>
			    </tr>
			    <tr>
			        <td colspan="2" align="center">
			            <input type="button" value="장바구니에 담기" 
			            onclick="javascript:location.href='addProduct.jsp?p_num=<%=pvo.getP_num()%>'"/>
			            <input type="button" value="장바구니 보기" 
			            onclick="javascript:location.href='cartList.jsp'"/>
			        </td>
			    </tr> 
		    </tbody>   
<%} %>
		</table>
</body>
</html>