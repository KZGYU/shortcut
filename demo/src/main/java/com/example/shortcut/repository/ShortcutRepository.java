package com.example.shortcut.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.shortcut.model.Shortcut;

public interface ShortcutRepository extends JpaRepository<Shortcut, Long> {
    // ショートカットの検索機能
    // カスタムクエリを使用して、名前、内容、またはコマンドに基づいてショートカットを検索します。
    // :searchTermは、クエリのパラメーターで、検索する文字列を表します。
    // Pageableオブジェクトは、ページネーションの情報を提供します（ページ番号、ページサイズなど）。
    @Query("SELECT s FROM Shortcut s WHERE " +
           "LOWER(s.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(s.content) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(s.command) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<Shortcut> search(String searchTerm, Pageable pageable);
}
