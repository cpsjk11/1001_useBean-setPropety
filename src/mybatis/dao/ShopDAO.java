package mybatis.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import mybatis.service.FactoryService;
import mybatis.vo.ProductVO;

public class ShopDAO {
	
	
	//useBean에서 호출하는 비지니스 로직(기능)
	public static ProductVO[] getList(String category) {
		ProductVO[] ar = null;
		
		// DB연결준비!!
		SqlSession ss = FactoryService.getFactory().openSession();
		
		// 몇개가 넘어올지 모르니 List를 통해서 받자!!
		List<ProductVO> list = ss.selectList("shop.c_search",category);
		
		// 유효성 검사
		if(list != null && !list.isEmpty()) {
			// list에 값이 제대로 들어온 경우! - null도 아니고 비어있지도 않은 상태!!
			
			// 이제 리스트 안에 크기 만큼 배열의 크기를 지정해 주자!!
			ar = new ProductVO[list.size()];
			
			// 크기를 준비 완료 했다면 현재 ar은 아무것도 들어가 있지 않으니 list의 있는 값 전부를 넘겨주자
			list.toArray(ar);
		}
		
		ss.close();
		
		return ar;
	}
}
