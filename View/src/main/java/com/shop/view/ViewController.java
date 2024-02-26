package com.shop.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
	
	@GetMapping("/view")
	public String viewPage() {
		return "view";
	}
	
	@GetMapping("/board")
	public String boardPage() {
		return "board";
	}
	
	@GetMapping("/login")
    public String loginPage() {
        return "login"; // 로그인 페이지로 이동
    }
	@RequestMapping("/pageNotFound")
    public String pageNotFound() {
        return "pageNotFound";
    }
}
