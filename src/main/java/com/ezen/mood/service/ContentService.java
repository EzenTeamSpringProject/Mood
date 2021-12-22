package com.ezen.mood.service;

import com.ezen.mood.domain.content.ContentRegDto;
import com.ezen.mood.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ContentService {

    private final ContentRepository contentRepository;

    public void register(ContentRegDto contentRegDto) {

    }
}
