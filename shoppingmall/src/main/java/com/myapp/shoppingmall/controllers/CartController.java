package com.myapp.shoppingmall.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myapp.shoppingmall.dao.Cart;
import com.myapp.shoppingmall.dao.ProductRepository;
import com.myapp.shoppingmall.entities.Product;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private ProductRepository productRepo;
	
	/**
	 * 제품의 id를 입력받아 세션에 카트리스트를 저장한다.
	 * @param id
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping("/add/{id}")
	@SuppressWarnings("unchecked")
	public String add(@PathVariable int id, HttpSession session, Model model) {
		// 0. 제품을 DB에서 꺼내기
		Product product = productRepo.getById(id);
		
		// 1. 이미 만들어진 장바구니가 없으면 세션에 그 제품을 저장하기		
		if(session.getAttribute("cart") == null) {    //이미 만들어진 장바구니가 없으면
			HashMap<Integer, Cart> cart = new HashMap<>(); // 맵으로<id,카트> 로 리스트를 만든다.
			cart.put(id, new Cart(id, product.getName(), product.getPrice(), 1, product.getImage()));
			session.setAttribute("cart", cart);
		} else { // 2. 이미 장바구니가 있을경우 ( (1) 그 제품이 담겨있을경우 (2) 없을경우 )			
			HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>)session.getAttribute("cart");
			if(cart.containsKey(id)) { //이미 장바구니에 그 제품이 있을경우
				int qty = cart.get(id).getQuantity(); //현재 카트의 수량
				cart.put(id, new Cart(id, product.getName(), product.getPrice(), ++qty, product.getImage()));
			} else {
				cart.put(id, new Cart(id, product.getName(), product.getPrice(), 1, product.getImage()));
				session.setAttribute("cart", cart);
			}
		}
		
		HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>)session.getAttribute("cart");
		
		int size = 0; //장바구 상품의 갯수
		int total = 0; //총 가격
		
		for(Cart item : cart.values()) { // 장바구니의 cart객체들을 반복
			size += item.getQuantity();
			total += item.getQuantity() * Integer.parseInt(item.getPrice()); //수량*가격 = 총가격
		}
		model.addAttribute("size", size);   //화면에 전달
		model.addAttribute("total", total); //화면에 전달
				
		return "cart_view";
	}
}
