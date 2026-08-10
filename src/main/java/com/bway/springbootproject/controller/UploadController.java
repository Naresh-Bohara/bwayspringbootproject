package com.bway.springbootproject.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	@GetMapping("/upload")
	public String getUpload() {
		return "upload-image";
	}

	@PostMapping("/upload")
	public String postUpload(@RequestParam("image") MultipartFile image, Model model) throws IOException {
		if (!image.isEmpty()) {
			long sizeinKB = image.getSize()/1024;
			
			if(sizeinKB > 200) {
				model.addAttribute("error", "sorry! max size is 200KB!");
				return "upload-image";
			}
			
			Files.copy(image.getInputStream(),
					Path.of("src/main/resources/static/images/" + image.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			model.addAttribute("msg", "added successfully!");
			return "upload-image";
		}
		model.addAttribute("error", "error uploading error!");
		return "upload-image";

	}
}
