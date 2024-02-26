package com.shop.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

public class ViewController {
	
	@GetMapping("/view")
	public String viewPage() {
		return "view";
	}
	
	@GetMapping("/board")
	public String boardPage() {
		return "board";
	}
}
