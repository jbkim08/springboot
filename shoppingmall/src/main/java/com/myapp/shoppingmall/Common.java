package com.myapp.shoppingmall;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.myapp.shoppingmall.dao.Cart;
import com.myapp.shoppingmall.dao.CategoryRepository;
import com.myapp.shoppingmall.dao.PageRepository;
import com.myapp.shoppingmall.entities.Category;
import com.myapp.shoppingmall.entities.Page;

//모든 컨트롤러에 적용(모든 페이지에 적용됨)
@ControllerAdvice
public class Common {
	
	@Autowired
	private PageRepository pageRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	//모델에 추가한다.
	@ModelAttribute
	public void sharedData(Model model, HttpSession session, Principal principal) {
		
		if(principal != null) { //인증된 상태를 모든 페이지에 전달
			model.addAttribute("principal", principal.getName()); //유저네임을 전달
		}
		
		// cpages에 모든 페이지들을 순서대로 담아서 전달
		List<Page> cpages = pageRepo.findAllByOrderBySortingAsc();
		List<Category> categories = categoryRepo.findAllByOrderBySortingAsc(); 
		// 현재 장바구니 상태 (없을때 false)
		boolean cartActive = false;
		
		if(session.getAttribute("cart") != null) { //장바구니가 있을 경우
			@SuppressWarnings("unchecked")
			HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>)session.getAttribute("cart");
			
			int size = 0; //장바구 상품의 갯수
			int total = 0; //총 가격
			
			for(Cart item : cart.values()) { // 장바구니의 cart객체들을 반복
				size += item.getQuantity();
				total += item.getQuantity() * Integer.parseInt(item.getPrice()); //수량*가격 = 총가격
			}
			model.addAttribute("csize", size);   //화면에 전달
			model.addAttribute("ctotal", total); //화면에 전달
			cartActive = true; 					//장바구니가 있음
		}
		
		model.addAttribute("cpages", cpages);
		model.addAttribute("ccategories", categories);		
		model.addAttribute("cartActive", cartActive); //장바구니 없으면 false , 있으면 true		
	}

}
