//アプリケーションのデータモデルを表すJavaクラス

package com.example.shortcut.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // このクラスがエンティティであることを示す
public class Shortcut {

    @Id // 主キーを示す
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDの自動生成戦略
    private Long id;

    private String name; // ショートカットの名前
    private String content; // ショートカットの内容
    private String command; // ショートカットのコマンド

    // コンストラクタ、ゲッター、セッターなどは省略

    // 必要に応じて、コンストラクタ、ゲッター、セッターをここに追加してください。
}
