package com.rpindv.backend_task.services;

import com.rpindv.backend_task.entities.Content;
import com.rpindv.backend_task.entities.ContentRates;
import com.rpindv.backend_task.entities.User;
import com.rpindv.backend_task.helpers.validators.AlreadyRatedContentException;
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

    public boolean checkIfAlreadyRatedContent(Long id_content, Integer id_user) {
        return contentRatesRepository.checkIfAlreadyRated(id_content, id_user);
    }

    @Transactional(rollbackOn = Exception.class)
    public void createContentRate(ContentRateDTO contentRates){
        if(!checkIfAlreadyRatedContent(contentRates.getContentId(), contentRates.getUserId())) {
            ContentRates saved = ContentRates.builder()
                    .idContent(contentRepository.findById(contentRates.getContentId()).orElseThrow(NotFoundException::new))
                    .idUser(userRepository.findById(contentRates.getUserId()).orElseThrow(NotFoundException::new))
                    .rating(contentRates.getRate())
                    .build();

            contentRatesRepository.save(saved);
        } else {
            throw new AlreadyRatedContentException();
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public void updateContentRate(Long rateId, ContentRateDTO contentRates){
        Optional<ContentRates> existing = contentRatesRepository.findById(rateId);
        existing.get().setRating(contentRates.getRate());
        contentRatesRepository.save(existing.get());
    }

    @Transactional(rollbackOn = Exception.class)
    public Boolean deleteContentRate(Long id){
        contentRatesRepository.findById(id).orElseThrow(NotFoundException::new);
        contentRatesRepository.deleteById(id);
        return true;
    }
}
