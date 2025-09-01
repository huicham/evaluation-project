package com.example.backend.api;

import com.example.backend.domain.Memo;
import com.example.backend.service.MemoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;

@RestController
@RequestMapping("/api/memos")
public class MemoController {

    private final MemoService memoService;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @GetMapping
    public Page<Memo> getAllMemos(@RequestParam(required = false) String keyword, Pageable pageable) {
        if (StringUtils.hasText(keyword)) {
            return memoService.searchMemos(keyword, pageable);
        }
        return memoService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Memo getMemoById(@PathVariable Long id) {
        return memoService.findById(id);
    }

    @PostMapping
    public Memo createMemo(@RequestBody Memo memo) {
        return memoService.save(memo);
    }

    @PutMapping("/{id}")
    public Memo updateMemo(@PathVariable Long id, @RequestBody Memo memoDetails) {
        return memoService.update(id, memoDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteMemo(@PathVariable Long id) {
        memoService.delete(id);
    }
}