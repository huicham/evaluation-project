package com.example.backend.api;

import com.example.backend.domain.Memo;
import com.example.backend.service.MemoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memos")
public class MemoController {

  private final MemoService memoService;

  public MemoController(MemoService memoService) {
    this.memoService = memoService;
  }

  @GetMapping
  public List<Memo> getAllMemos() {
    return memoService.findAll();
  }
  
  // --- 추가된 부분 시작 ---
  @GetMapping("/{id}")
  public Memo getMemoById(@PathVariable Long id) {
    return memoService.findById(id);
  }
  // --- 추가된 부분 끝 ---

  @PostMapping
  public Memo createMemo(@RequestBody Memo memo) {
    return memoService.save(memo);
  }

  // --- 추가된 부분 시작 ---
  @PutMapping("/{id}")
  public Memo updateMemo(@PathVariable Long id, @RequestBody Memo memoDetails) {
    return memoService.update(id, memoDetails);
  }
  // --- 추가된 부분 끝 ---

  @DeleteMapping("/{id}")
  public void deleteMemo(@PathVariable Long id) {
    memoService.delete(id);
  }
}