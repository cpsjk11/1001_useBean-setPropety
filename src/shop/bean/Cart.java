package shop.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mybatis.vo.ProductVO;

public class Cart {
/*
	 카트의 기능분석
	 1) 제품(ProductVO)을 담을(저장) 수 있는 공간 : List<ProductVO>
	 2) 1)에 제품을 저장하는 기능 
	 3) 1)에서 제품을 검색하는 기능
	 4) 1)에서 제품 삭제하는 기능
	 5) 3)에서 검색된 제품의 수량을 변경하는 기능
	 6) 1)에 있는 모든 제품들을 배열로 반환하는 기능
	 7) 총액 계산
	 
	 위 분석에 따른 카트의 속성(멤버변수)
	 	- List<ProductVO>
	 	- int totalPrice;
	 	
	 카트의 기능(멤버메서드)
	 	- searchProduct: 제품 검색기능
	 	- addProduct: 제품 추가기능
	 	- delProduct: 제품 삭제기능
	 	- chageCount: 수량 변경기능
	 	- getList: 카트에 있는 모든 제품을 배열로 반환한다.
*/
	
	private List<ProductVO> list;
	
	public Cart() { // 생성자
		list = new ArrayList<ProductVO>();
	}
	
	// 카트에서 특정 제품을 검색하는 기능
	public ProductVO searProduct(String pnum) {
		/*
		for(ProductVO vo : list) {
			if(vo.getP_num().equals(pnum)) {
				return vo;
			}
		}
		return null;
	}
	*/
		// 검색속도를 높이기 위해 또는 List에 있는 객체를 찾아서
		// 반복문을 사용한다. 이때 검색된 객체를 삭제하면 오류가 발생할 수 있다.
		// 그렇다면 Iterator를 반복하여 List에서 삭제하면 된다.
		Iterator<ProductVO> it = list.iterator();
		
		while(it.hasNext()) {// 현재 반복자(커서)의 위치에서 바로 다음칸에 요소가 있다면
							// true! 없다면 false!
			ProductVO vo = it.next(); // 반복자가 다음칸으로 이동하여 그곳에
									// 있는 자원을 vo에 저장해 준다.
			if(vo.getP_num().equals(pnum)) {
				return vo;
			}
			
		}//while문의 끝
		return null;
	}
	
	// 카트에 저장된 모든 제품들을 하나의 배열로 반환하는 기능!
	public ProductVO[] getList() {
		ProductVO[] ar = null;
		if(!list.isEmpty()) {
			ar = new ProductVO[list.size()];
			list.toArray(ar);
		}
		return ar;
	}
	
	// 제품을 카트에 저장하는 기능
	public void addProduct(ShopBean sb, String pnum) {
		// 현재 인자로 넘어온 선택된 제품의 번호가 
		// 이미 카트에 저장된 제품일 수도 있기 때문에
		// 먼저 카트에서 검색하자!
		ProductVO vo = searProduct(pnum);
		
		// 위의 vo가 null이 아닐 경우는 앞서 카트에 담은 제품이다.
		// 이때는 수량만 1증가 시킨다.
		if(vo != null) {
			//먼저 기존 제품의 수량을 얻어낸다.
			int q = vo.getQuant();
			vo.setQuant(q+1);
			return; //  더 이상 하지 않고 제어권 반환!
		}
		
		// 제어권이 여기에 왔다면 카트에 같은 제품이 없는 경우다.
		// 그러므로 진열대에서 제품을 얻어낸다.
		// 먼저 제품번호를 진열대에 저장
		sb.setP_num(pnum);
		vo = sb.getProduct(); // 진열대에서 원하는 제품을 가져온다.
		
		// 가져온 제품의 수량을 1로 지정한다.
		vo.setQuant(1);
		
		// 카트에 저장소 List에 저장
		list.add(vo);
	}
	
	// 카트에서 특정제품을 검섹하여 삭제하는 기능
	public boolean delProduct(String pnum) {
		boolean value = false;
		ProductVO vo = searProduct(pnum);
		if(vo != null) {
			list.remove(vo); // 삭제
			value = true;
		}
		return value;
	}
	
	// 카트에서 원하는 제품을 검색한 후 수량만 변경하는 기능
	public void changeCount(String pnum, int q) {
		ProductVO vo = searProduct(pnum);
		if(vo != null) 
			vo.setQuant(q);// 이때 수량만 변경되는 것이 아니라 총금액도 변경됨!
		
	}
}














