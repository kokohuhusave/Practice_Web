package com.shop.controller;

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
    public String login() {
        return "login";
    }

	@GetMapping("/")
    public String home() {
        return "home";
    }
	
	@RequestMapping("/pageNotFound")
    public String pageNotFound() {
        return "pageNotFound";
    }
	
}
