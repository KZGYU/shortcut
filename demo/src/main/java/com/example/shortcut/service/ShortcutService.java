package com.example.shortcut.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.shortcut.model.Shortcut;
import com.example.shortcut.repository.ShortcutRepository;

@Service
public class ShortcutService {

	@Autowired
	private ShortcutRepository shortcutRepository;

	//特定のIDでショートカットを検索するメソッド
	public Shortcut findById(Long id) {
		return shortcutRepository.findById(id).orElse(null);
	}
	
	
	public void delete(Long id) {
	    shortcutRepository.deleteById(id);
	}


	//ショートカットを保存（更新）するメソッド
	public void save(Shortcut shortcut) {
		shortcutRepository.save(shortcut);
	}

	// ショートカットのページネーションと検索
	// keywordは検索用の文字列、pageはページ番号、sizeは1ページあたりのアイテム数です。
	public Page<Shortcut> listAll(String keyword, int page, int size) {
		if (keyword != null && !keyword.isEmpty()) {
			// 検索キーワードがある場合は、searchメソッドを使用して結果を取得します。
			return shortcutRepository.search(keyword, PageRequest.of(page, size));
		}
		// 検索キーワードがない場合は、すべてのショートカットを取得します。
		return shortcutRepository.findAll(PageRequest.of(page, size));

	}
	
}
