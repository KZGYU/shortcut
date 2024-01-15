import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.hibernate.mapping.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.shortcut.model.Shortcut;
import com.example.shortcut.service.QuestionService;

@Controller
public class EclipseQuizController {
	

	// 現在の問題番号を表す変数
	private int currentQuestionIndex = 0;

	@GetMapping("/eclipseQuiz")
	public String eclipseQuiz(Model model) {
		// 現在の問題番号を使って問題を取得
		Question question = QuestionService.getQuestionByIndex(currentQuestionIndex);
		if (question != null) {
			model.addAttribute("question", question);
			List<Shortcut> choices = QuestionService.getChoices(question.getAnswerId());
			model.addAttribute("choices", choices);
		}
		return "eclipseQuiz";
	}

	@PostMapping("/eclipseQuiz/next")
	public String nextQuestion() {
		// 現在の問題番号をインクリメントして次の問題へ
		currentQuestionIndex++;
		// クイズが終了したかどうかをチェック
		if (currentQuestionIndex >= QuestionService.getTotalQuestions()) {
			// クイズが終了した場合、結果ページにリダイレクト
			return "redirect:/quizResult";
		}
		// 次の問題へのリダイレクト
		return "redirect:/eclipseQuiz";
	}


}
