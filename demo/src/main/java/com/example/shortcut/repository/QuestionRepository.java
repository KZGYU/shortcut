package com.example.shortcut.repository;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    // 必要に応じて追加のクエリメソッドをここに定義
}

