package com.bway.springbootproject.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bway.springbootproject.repository.ProductRepository;
 
@Controller
public class GalleryController {
	@Autowired
	private ProductRepository prodRepo;
	
	@GetMapping("/gallery")
	public String gallery(Model model) {
		String[] imgNames = new File("src/main/resources/static/images").list();
		model.addAttribute("imgList", imgNames);
		return "gallery";
	}
	
	@GetMapping("product-gallery")
	public String getProductGallery(Model model) {
		model.addAttribute("prodList",prodRepo.findAll());
		return "product-gallery";
	}
}
