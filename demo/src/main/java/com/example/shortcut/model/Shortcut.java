
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
import lombok.Data;

@Entity
@Table(name = "shortcuts") // データベースのテーブル名を指定
@Data
public class Shortcut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//新しいエンティティがデータベースに保存される際に、IDが自動的に生成
    @Column(name = "id")
    private Integer id;

    @Column(name = "name") // データベースのカラム名を指定
    private String name;

    @Column(name = "content")
    private String content;

    @Column(name = "command")
    private String command;

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
