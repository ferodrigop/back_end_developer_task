package com.rpindv.backend_task.controllers;

import com.rpindv.backend_task.entities.ContentRates;
import com.rpindv.backend_task.helpers.validators.NotFoundException;
import com.rpindv.backend_task.models.ContentRateDTO;
import com.rpindv.backend_task.services.ContentRatesService;
import com.rpindv.backend_task.services.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ContentRatesController {
    private final ContentRatesService contentRatesService;
    private final ContentService contentService;

    @GetMapping("rate/get-overall/{contentId}")
    public ResponseEntity getOverallRate(@PathVariable Long contentId){
        if(contentService.getContentById(contentId).isEmpty()) {
            throw new NotFoundException();
        }

        return ResponseEntity.status(HttpStatus.OK).body(contentRatesService.getContentAverageRate(contentId));
    }

    @PostMapping("rate/create")
    public ResponseEntity createContentRate(@RequestBody ContentRateDTO contentRates){
        contentRatesService.createContentRate(contentRates);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("rate/update/{rateId}")
    public ResponseEntity updateContentRate(@PathVariable("rateId")Long rateId, @RequestBody ContentRateDTO contentRates){
        try {
            contentRatesService.updateContentRate(rateId, contentRates);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("rate/delete/{rateId}")
    public ResponseEntity deleteById(@PathVariable Long rateId){
        if(!contentRatesService.deleteContentRate(rateId)){
            throw new NotFoundException();
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
