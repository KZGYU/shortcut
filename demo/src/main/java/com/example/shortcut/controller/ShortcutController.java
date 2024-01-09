package com.example.shortcut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shortcut.model.Shortcut;
import com.example.shortcut.service.ShortcutService;

@Controller // このクラスがコントローラーであることを示す
public class ShortcutController {

	@Autowired // SpringによるShortcutServiceの自動注入を指示
	private ShortcutService shortcutService;

	@GetMapping("/") // ルートURLへのGETリクエストを処理
	public String index() {
		return "index"; // "index"テンプレート（HTMLページ）を表示
	}

	@GetMapping("/shortcuts") // "/shortcuts" URLへのGETリクエストを処理
	public String shortcuts() {
		return "shortcuts"; // "shortcuts"テンプレートを表示
	}

	@GetMapping("/shortcuts/edit") // "/shortcuts/edit" URLへのGETリクエストを処理
	public String showUpdateForm(@RequestParam("id") Long id, Model model) {
		Shortcut shortcut = shortcutService.findById(id); // IDに基づいてShortcutを取得
		model.addAttribute("shortcut", shortcut); // モデルにShortcutを追加
		return "updateShortcut"; // "updateShortcut"テンプレートを表示
	}

	@PostMapping("/shortcuts/update") // "/shortcuts/update" URLへのPOSTリクエストを処理
	public String updateShortcut(@ModelAttribute("shortcut") Shortcut shortcut) {
		shortcutService.save(shortcut); // Shortcutを保存
		return "redirect:/eclipseShortcuts"; // "/eclipseShortcuts"にリダイレクト
	}

	@GetMapping("/shortcuts/new") // "/shortcuts/new" URLへのGETリクエストを処理
	public String showAddForm(Model model) {
		model.addAttribute("shortcut", new Shortcut()); // 新しいShortcutをモデルに追加
		return "addShortcut"; // "addShortcut"テンプレートを表示
	}

	@PostMapping("/shortcuts/add") // "/shortcuts/add" URLへのPOSTリクエストを処理
	public String addShortcut(@ModelAttribute Shortcut shortcut) {
		shortcutService.save(shortcut); // Shortcutを保存
		return "redirect:/eclipseShortcuts"; // "/eclipseShortcuts"にリダイレクト
	}

	@GetMapping("/eclipseShortcuts") // "/eclipseShortcuts" URLへのGETリクエストを処理
	public String viewShortcutPage(Model model,
			@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		Page<Shortcut> listShortcuts = shortcutService.listAll(keyword, page, size); // ページネーションと検索結果を取得
		model.addAttribute("listShortcuts", listShortcuts); // モデルに結果を追加
		model.addAttribute("keyword", keyword);
		return "eclipseShortcuts"; // "eclipseShortcuts"テンプレートを表示
	}

	@GetMapping("/eclipseQuiz") // "/eclipseQuiz" URLへのGETリクエストを処理
	public String eclipseQuiz() {
		return "eclipseQuiz"; // "eclipseQuiz"テンプレートを表示
	}

	@GetMapping("/excelShortcuts") // "/excelShortcuts" URLへのGETリクエストを処理
	public String excelShortcuts() {
		return "excelShortcuts"; // "excelShortcuts"テンプレートを表示
	}

	@GetMapping("/excelQuiz") // "/excelQuiz" URLへのGETリクエストを処理
	public String excelQuiz() {
		return "excelQuiz"; // "excelQuiz"テンプレートを表示
	}
}
