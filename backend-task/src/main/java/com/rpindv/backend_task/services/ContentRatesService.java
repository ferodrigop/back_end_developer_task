package com.rpindv.backend_task.services;

import com.rpindv.backend_task.entities.ContentRates;
import com.rpindv.backend_task.helpers.validators.NotFoundException;
import com.rpindv.backend_task.models.ContentRateDTO;
import com.rpindv.backend_task.repositories.ContentRatesRepository;
import com.rpindv.backend_task.repositories.ContentRepository;
import com.rpindv.backend_task.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContentRatesService {
    private final ContentRatesRepository contentRatesRepository;
    private final ContentRepository contentRepository;
    private final UserRepository userRepository;

    public Optional<ContentRates> getContentRateById(Long id) {
        return contentRatesRepository.findById(id);
    }

    public String getContentAverageRate (Long id) {
        return contentRatesRepository.getAverageContentRate(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public void createContentRate(ContentRateDTO contentRates){
        ContentRates saved = ContentRates.builder()
                .id_content(contentRepository.findById(contentRates.getContentId()).orElseThrow(() -> new RuntimeException("Content not found")))
                .id_user(userRepository.findById(contentRates.getUserId()).orElseThrow(() -> new RuntimeException("User not found")))
                .rating(contentRates.getRate())
                .build();

        contentRatesRepository.save(saved);
    }

    @Transactional(rollbackOn = Exception.class)
    public void updateContentRate(Long rateId, ContentRateDTO contentRates){
        Optional<ContentRates> existing = contentRatesRepository.findById(rateId);
        existing.get().setRating(contentRates.getRate());
        contentRatesRepository.save(existing.get());
    }

    @Transactional(rollbackOn = Exception.class)
    public Boolean deleteContentRate(Long id){
        ContentRates contentRates = contentRatesRepository.findById(id).orElseThrow(NotFoundException::new);
        contentRatesRepository.deleteById(id);
        return true;
    }
}
