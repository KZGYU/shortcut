package com.example.shortcut.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shortcut.model.Shortcut;
import com.example.shortcut.repository.QuestionRepository;
import com.example.shortcut.repository.ShortcutRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private ShortcutRepository shortcutRepository; // Shortcutデータにアクセスするためのリポジトリ

	private static List<Question> questions; // すべての問題を格納するリスト

	//問題文をランダムに表示させる
	public Question getRandomQuestion() {
		List<Question> allQuestions = questionRepository.findAll();
		if (allQuestions.isEmpty()) {
			return null; // 質問がない場合はnullを返す
		}

		Random random = new Random();
		int randomIndex = random.nextInt(allQuestions.size());
		return allQuestions.get(randomIndex); // ランダムな質問を返す

	}

	// コンストラクタなどを使用してquestionsリストに問題をセットするコードを実装
    public static Question getQuestionByIndex(int index) {
        if (index >= 0 && index < questions.size()) {
            return questions.get(index);
        } else {
            return null; // インデックスが範囲外の場合はnullを返すなどの処理を追加できます
        }
    }
    
    public static int getTotalQuestions() {
        return questions.size();
    }

	// 正解のショートカットとランダムな不正解の選択肢を生成して返すロジックを実装
	public List<Shortcut> getChoices(Long answerId) {
		List<Shortcut> allShortcuts = shortcutRepository.findAll();
		List<Shortcut> choices = new ArrayList<>();

		// 正解のショートカットを追加
		Shortcut correctAnswer = allShortcuts.stream()
				.filter(s -> s.getId().equals(answerId))
				.findFirst()
				.orElse(null);

		if (correctAnswer != null) {
			choices.add(correctAnswer);
			allShortcuts.remove(correctAnswer); // 正解をリストから削除
		}

		// ランダムな不正解の選択肢を追加
		Collections.shuffle(allShortcuts);
		choices.addAll(allShortcuts.stream().limit(2).collect(Collectors.toList()));

		// 選択肢をシャッフルして順番をランダムにする
		Collections.shuffle(choices);
		return choices;
	}

	// ユーザーの選択した回答が正解かどうかをチェックするロジック
	public boolean checkAnswer(Long questionId, Long selectedChoiceId) {
	    Question question = questionRepository.findById(questionId).orElse(null);
	    if (question == null) {
	        return false; // 問題が見つからない場合は不正解とする
	    }
	    
	    // ユーザーが選択した選択肢を取得
	    Shortcut selectedChoice = shortcutRepository.findById(selectedChoiceId).orElse(null);
	    if (selectedChoice == null) {
	        return false; // 選択肢が見つからない場合は不正解とする
	    }
	    
	    // ユーザーが選択した選択肢が正解かどうかをチェック
	    Long correctChoiceId = question.getAnswerId();
	    return selectedChoiceId.equals(correctChoiceId);
	}

}