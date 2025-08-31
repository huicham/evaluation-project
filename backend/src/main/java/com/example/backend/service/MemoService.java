package com.example.backend.service;

import com.example.backend.domain.Memo;
import com.example.backend.repository.MemoRepository;
import jakarta.persistence.EntityNotFoundException; 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import java.util.List;

@Service
public class MemoService {

    private final MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    public List<Memo> findAll() {
        return memoRepository.findAll();
    }

    
    @Transactional(readOnly = true)
    public Memo findById(Long id) {
        return memoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Memo not found with id: " + id));
    }
    

    public Memo save(Memo memo) {
        return memoRepository.save(memo);
    }

    
    @Transactional
    public Memo update(Long id, Memo memoDetails) {
        Memo memo = findById(id); 
        memo.setTitle(memoDetails.getTitle());
        memo.setContent(memoDetails.getContent());
        return memoRepository.save(memo); 
    }
    

    public void delete(Long id) {
        memoRepository.deleteById(id);
    }
}