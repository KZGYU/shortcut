package com.example.shortcut.controller;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.hibernate.engine.internal.Collections;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shortcut.model.Shortcut;
import com.example.shortcut.service.QuestionService;
import com.example.shortcut.service.ShortcutService;


@Controller // このクラスがコントローラーであることを示す
public class ShortcutController {

	@Autowired // SpringによるShortcutServiceの自動注入を指示
	private ShortcutService shortcutService;

	@Autowired
	private QuestionService questionService; // 質問データを取得するためのサービス

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

	@GetMapping("/shortcuts/delete/{id}")
	public String deleteShortcut(@PathVariable Long id) {
		shortcutService.delete(id);
		return "redirect:/eclipseShortcuts"; // 削除後にショートカット一覧ページにリダイレクト
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

	@GetMapping("/eclipseQuiz")
	public String eclipseQuiz(Model model) {
	    Question question = questionService.getRandomQuestion();
	    if (question != null) {
	        model.addAttribute("question", question);
	        // ランダムに選択肢を取得
	        List<Shortcut> choices = questionService.getRandomChoices(question.getAnswerId(), 2); // 2つの不正解選択肢を取得
	        // 正解選択肢も取得
	        Shortcut correctChoice = shortcutService.findById(question.getAnswerId());
	        // ランダムな選択肢を追加
	        choices.add(correctChoice);
	        // 選択肢をシャッフル
	        Collections.shuffle(choices);
	        model.addAttribute("choices", choices);
	    }
	    return "eclipseQuiz"; 
	}
	
	
	@PostMapping("/eclipseQuiz/submit")
	public String submitAnswer(@RequestParam("selectedChoice") Long selectedChoiceId, Model model) {
	    Question question = questionService.getRandomQuestion(); // 新しい問題を取得
	    if (question != null) {
	        // 選択された選択肢のIDを取得
	        Shortcut selectedChoice = shortcutService.findById(selectedChoiceId);
	        model.addAttribute("selectedChoice", selectedChoice);

	        // 正解選択肢を取得
	        Shortcut correctChoice = shortcutService.findById(question.getAnswerId());
	        model.addAttribute("correctChoice", correctChoice);
	        
	        if (selectedChoice.getId().equals(correctChoice.getId())) {
	            // 選択肢が正解の場合
	            model.addAttribute("isCorrect", true);
	        } else {
	            // 不正解の場合
	            model.addAttribute("isCorrect", false);
	        }

	        // 新しい問題と選択肢を取得して再度クイズページを表示
	        List<Shortcut> choices = questionService.getRandomChoices(question.getAnswerId(), 2);
	        choices.add(correctChoice);
	        Collections.shuffle(choices);
	        model.addAttribute("question", question);
	        model.addAttribute("choices", choices);
	    }
	    return "eclipseQuiz"; // Thymeleafテンプレートの名前
	}

	

	@GetMapping("/excelShortcuts") // "/excelShortcuts" URLへのGETリクエストを処理
	public String excelShortcuts() {
		return "excelShortcuts"; // "excelShortcuts"テンプレートを表示
	}

	@GetMapping("/excelQuiz") // "/excelQuiz" URLへのGETリクエストを処理
	public String excelQuiz() {
		return "excelQuiz"; // "excelQuiz"テンプレートを表示する
	}
}
