package com.example.backend.repository;

import com.example.backend.domain.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {
    Page<Memo> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}