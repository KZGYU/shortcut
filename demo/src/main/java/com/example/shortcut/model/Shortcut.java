package com.example.shortcut.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "shortcuts")
@Data // Lombokの@Dataアノテーションを使用
public class Shortcut {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") // idフィールドに@Columnアノテーションを追加
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "content")
	private String content;

	@Column(name = "command")
	private String command;

	// Lombokの@Dataにより、ゲッター、セッター、toStringメソッドは自動生成される
}
