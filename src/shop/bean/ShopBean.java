package shop.bean;

import mybatis.dao.ShopDAO;
import mybatis.vo.ProductVO;

public class ShopBean {
	
	// 사용자가 선택하는 category값! 이것은 <jsp:setProperty...로 인해 자동 채워진다.
	private String category;
	
	// 카테고리 값으로 제품들을 선별하여 배열로 저장하는 곳
	private ProductVO[] p_list; // 사용자가 선택한 제품들의 목록(진열대)
	
	private String p_num; // 제품 상세보기 기능에서 받는 제품번호를 저장할 곳

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public ProductVO[] getP_list() {
		return p_list;
	}

	public void setP_list(ProductVO[] p_list) {
		this.p_list = p_list;
	}

	public String getP_num() {
		return p_num;
	}

	public void setP_num(String p_num) {
		this.p_num = p_num;
	}
	
	// 제품목록(진열대)을 ShopDAO를 통해 생성해야 한다.
	public void searchProduct() {
		// 현재 함수가 불려지기 전에 category변수는 채워진 상태여야 한다.
		p_list = ShopDAO.getList(category);
	}
	
	// 사용자가 상세보기를 하기위해 제품번호를 이용하게 된다.
	// 그래서 원하는 제품을 p_list에서 검색한 후 반환하는 기능
	public ProductVO getProduct() { // 이런 방식을 사용하면 검색을 할때마다 DB와 연결해서 보여주는 것이 아닌 한번만 연결하고 그 값을 list에 저장해 필요할떄 마다 
							// list에서 꺼내서 확인하는 방식이다 이렇게하면 서버에 부담이 덜해진다.
		ProductVO vo = null;
		for(ProductVO pvo : p_list) {
			if(pvo != null) {
				if(pvo.getP_num().equals(p_num)) {
					vo = pvo;
					break;//가장 가까운 반복문 탈출
				}
			}
		}
		//진열대(p_list)에서 멤버변수인 p_num으로 제품을 검색한다
		
		return vo;
	}
	
}
