package com.myapp.shoppingmall.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myapp.shoppingmall.dao.CategoryRepository;
import com.myapp.shoppingmall.dao.ProductRepository;
import com.myapp.shoppingmall.entities.Category;
import com.myapp.shoppingmall.entities.Product;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private CategoryRepository categoryRepo;

	@GetMapping
	public String index(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {

		int perPage = 6; //한 페이지에 6개 출력 (최대 6개)
		Pageable pageable = PageRequest.of(page, perPage); // 표시할페이지, 한페이지에 몇개(6)
		//.data.domain.Page 
		Page<Product> products = productRepo.findAll(pageable);
		List<Category> categories = categoryRepo.findAll();
		
		HashMap<Integer, String> cateIdAndName = new HashMap<>();
		
		for(Category category : categories) {
			cateIdAndName.put(category.getId(), category.getName());
		}

		model.addAttribute("products", products);
		model.addAttribute("cateIdAndName", cateIdAndName);
		
		// 페이지를 보여주기 위해 계산
		long count = productRepo.count(); //전체 갯수(long타입 리턴)
		double pageCount = Math.ceil( (double)count / (double)perPage ); // 13 / 6 = 2.12 (총페이지숫자)
		
		model.addAttribute("pageCount", (int)pageCount); //총페이지
		model.addAttribute("perPage", perPage);			//페이지당 표시 아이템수
		model.addAttribute("count", count);				//총 아이템 갯수
		model.addAttribute("page", page);				//현재 페이지
		
		return "admin/products/index";
	}

	@GetMapping("/add")
	public String add(@ModelAttribute Product product, Model model) {

		List<Category> categories = categoryRepo.findAll();

		model.addAttribute("categories", categories);
		// 상품을 추가하는 add 페이지에 제품객체와 제품의 카테고리를 선택할수 있도록 카테고리 리스트 전달
		return "admin/products/add";
	}

	@PostMapping("/add")
	public String add(@Valid Product product, BindingResult bindingResult, MultipartFile file, RedirectAttributes attr,
			Model model) throws IOException {

		if (bindingResult.hasErrors()) {
			List<Category> categories = categoryRepo.findAll();
			model.addAttribute("categories", categories);
			return "admin/products/add"; // 유효성 검사 에러시 다시 되돌아감
		}

		boolean fileOk = false;
		byte[] bytes = file.getBytes(); // 업로드된 이미지 파일의 데이터
		String fileName = file.getOriginalFilename(); // 파일의 이름
		Path path = Paths.get("src/main/resources/static/media/" + fileName); // 파일을 저장할 위치와 이름까지

		if (fileName.endsWith("jpg") || fileName.endsWith("png")) {
			fileOk = true; // 확장자가 .jpg , .png 만 OK.
		}
		
		// 성공적으로 추가됨
		attr.addFlashAttribute("message", "상품이 성공적으로 추가됨!");
		attr.addFlashAttribute("alertClass", "alert-success");
		// 슬러그 만들기
		String slug = product.getName().toLowerCase().replace(" ", "-");
		// 똑같은 상품명이 있는지 검사
		Product productExists = productRepo.findByName(product.getName());

		if(!fileOk) { //파일 업로드가 안됬거나 확장자가 png,jpg 가 아님
			attr.addFlashAttribute("message", "이미지는 jpg나 png를 사용해 주세요");
			attr.addFlashAttribute("alertClass", "alert-danger");
			attr.addFlashAttribute("product", product);
		} 
		else if (productExists != null) { //이미 상품명이 DB에 존재함
			attr.addFlashAttribute("message", "상품이 이미 있습니다. 다른 이름으로 등록해 주세요");
			attr.addFlashAttribute("alertClass", "alert-danger");
			attr.addFlashAttribute("product", product);
		}
		else { //상품과 이미지 파일을 저장한다.
			product.setSlug(slug);
			product.setImage(fileName); //이미지는 파일의 이름만 입력(주소는 /media/폴더 이므로 동일)
			productRepo.save(product);  //제품을 저장
			
			Files.write(path, bytes); // (저장주소, 데이터 )
		}
		
		return "redirect:/admin/products/add";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		
		Product product = productRepo.getById(id);
		List<Category> categories = categoryRepo.findAll();
		
		model.addAttribute("categories", categories);
		model.addAttribute("product", product);
		return "admin/products/edit";
	}	
	
	@PostMapping("/edit")
	public String edit(@Valid Product product, BindingResult bindingResult, 
				MultipartFile file, RedirectAttributes attr, Model model) throws IOException {
		// 미리 id로 수정전의 제품을 불러옴
		Product currentProduct = productRepo.getById(product.getId());
		
		if (bindingResult.hasErrors()) {
			List<Category> categories = categoryRepo.findAll();
			model.addAttribute("categories", categories);
			if(product.getImage() == null) product.setImage(currentProduct.getImage());
			return "admin/products/edit"; // 유효성 검사 에러시 다시 되돌아감
		}

		boolean fileOk = false;
		byte[] bytes = file.getBytes(); // 업로드된 이미지 파일의 데이터
		String fileName = file.getOriginalFilename(); // 파일의 이름
		Path path = Paths.get("src/main/resources/static/media/" + fileName); // 파일을 저장할 위치와 이름까지

		if(!file.isEmpty()) { // 새 이미지 파일이 있으면
			if (fileName.endsWith("jpg") || fileName.endsWith("png")) {
				fileOk = true; // 확장자가 .jpg , .png 만 OK.
			}
		} else { // 이미지는 수정 안함
			fileOk = true; // 기존 이미지 사용
		}
		
		// 성공적으로 수정됨 메세지
		attr.addFlashAttribute("message", "상품이 성공적으로 수정됨!");
		attr.addFlashAttribute("alertClass", "alert-success");
		// 슬러그 만들기
		String slug = product.getName().toLowerCase().replace(" ", "-");
		// 똑같은 상품명이 있는지 검사
		Product productExists = productRepo.findByNameAndIdNot(product.getName(), product.getId());

		if(!fileOk) { //파일 업로드가 안됬거나 확장자가 png,jpg 가 아님
			attr.addFlashAttribute("message", "이미지는 jpg나 png를 사용해 주세요");
			attr.addFlashAttribute("alertClass", "alert-danger");
			attr.addFlashAttribute("product", product);
		} 
		else if (productExists != null) { //이미 상품명이 DB에 존재함
			attr.addFlashAttribute("message", "상품이 이미 있습니다. 다른 이름으로 등록해 주세요");
			attr.addFlashAttribute("alertClass", "alert-danger");
			attr.addFlashAttribute("product", product);
		}
		else { //상품과 이미지 파일을 저장한다.
			product.setSlug(slug);
			
			if(!file.isEmpty()) {
				Path currentpath = Paths.get("src/main/resources/static/media/" + currentProduct.getImage());
				Files.delete(currentpath); // 새파일 있기 때문에 기존 파일을 삭제
				product.setImage(fileName);
				Files.write(path, bytes); // 새 파일을 저장
			} else {
				product.setImage(currentProduct.getImage());
			}
			
			productRepo.save(product);
		}
		
		return "redirect:/admin/products/edit/" + product.getId();
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) throws IOException {
		// id로 상품을 삭제하기 전에 먼저 id로 제품객체를 불러와서 이미지 파일을 삭제한후 제품 삭제	
		Product currentProduct = productRepo.getById(id) ;
		Path currentPath = Paths.get("src/main/resources/static/media/" + currentProduct.getImage());
		
		Files.delete(currentPath); //파일 먼저 삭제
		productRepo.deleteById(id); //제품 삭제
		
		redirectAttributes.addFlashAttribute("message", "성공적으로 삭제 되었습니다.");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");		
		
		return "redirect:/admin/products";
	}

}
