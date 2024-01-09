package com.example.shortcut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shortcut.model.Shortcut;
import com.example.shortcut.service.ShortcutService;

@Controller
public class ShortcutController {

	@Autowired //Spring Frameworkの依存性注入（Dependency Injection）機能
	private ShortcutService shortcutService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/shortcuts")
	public String shortcuts() {
		return "shortcuts";
	}

	@GetMapping("/eclipseShortcuts")
	public String viewShortcutPage(Model model,
			@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		
		
		
		//サービス層からページネーションと検索結果を取得します
		Page<Shortcut> listShortcuts = shortcutService.listAll(keyword, page, size);
		//結果をモデルに追加して、ビューで使用できるようにします
		model.addAttribute("listShortcuts",listShortcuts);
		model.addAttribute("keyword",keyword);
		return "eclipseShortcuts";//Thymeleafテンプレートの名前を返す
		
	}

	@GetMapping("/eclipseQuiz")
	public String eclipseQuiz() {
		return "eclipseQuiz";
	}

	@GetMapping("/excelShortcuts")
	public String excelShortcuts() {
		return "excelShortcuts";
	}

	@GetMapping("/excelQuiz")
	public String excelQuiz() {
		return "excelQuiz";
	}
}