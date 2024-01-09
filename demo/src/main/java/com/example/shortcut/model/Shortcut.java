
//    @Entityアノテーションは、このクラスがエンティティであることを示します。
//    @Table(name = "shortcuts")は、このエンティティがマッピングされるデータベースのテーブル名を指定します。
//    @Idと@GeneratedValue(strategy = GenerationType.IDENTITY)は、主キーとその自動生成戦略を定義します。
//    @Column(name = "...")アノテーションは、各フィールドがデータベースのどのカラムに対応するかを指定します。



package com.example.shortcut.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "shortcuts") // データベースのテーブル名を指定
public class Shortcut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name") // データベースのカラム名を指定
    private String name;

    @Column(name = "content")
    private String content;

    @Column(name = "command")
    private String command;

    // コンストラクタ
    public Shortcut() {
    }

    // ゲッターとセッター
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    // toStringメソッド（オプショナル）
    @Override
    public String toString() {
        return "Shortcut{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", command='" + command + '\'' +
                '}';
    }
}
