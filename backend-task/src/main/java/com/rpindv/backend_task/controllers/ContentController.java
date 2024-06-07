package com.rpindv.backend_task.controllers;

import com.rpindv.backend_task.entities.Content;
import com.rpindv.backend_task.helpers.validators.NotFoundException;
import com.rpindv.backend_task.models.ContentDTO;
import com.rpindv.backend_task.services.ContentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@RestController
public class ContentController {
    private final ContentService contentService;

    @GetMapping("content/get/{contentId}")
    public ResponseEntity<Resource> getContentById(@PathVariable("contentId") Long contentId){
        try {
            Content content = contentService.getContentById(contentId).orElseThrow(NotFoundException::new);
            Path file = Paths.get(content.getContent_path()).resolve(content.getContent_name()).normalize();
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() && resource.isReadable()) {
                String contentType = Files.probeContentType(file);
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }
                return ResponseEntity.ok()
                        .contentType(org.springframework.http.MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("content/upload/single")
    public ResponseEntity singleFileUpload(@RequestPart("file") MultipartFile file,@Valid @RequestPart("content") ContentDTO content) {
        try {
            contentService.createContent(file, content.getTitle(), content.getDescription(), content.getCategory(), 2);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("content/upload/multiple")
    public ResponseEntity multipleFilesUpload(@RequestParam("files") MultipartFile[] files,@Valid @RequestPart("content") ContentDTO content) {
        for (MultipartFile file : files) {
            try {
                contentService.createContent(file, content.getTitle(), content.getDescription(), content.getCategory(), 2);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("content/update/{contentId}")
    public ResponseEntity updateById(@PathVariable("contentId")Long contentId, @Valid @RequestBody ContentDTO content){

        if( contentService.getContentById(contentId).isEmpty()){
            throw new NotFoundException();
        }
        contentService.updateContentById(contentId, content);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("content/delete/{contentId}")
    public ResponseEntity deleteById(@PathVariable("contentId") Long contentId){

        if(! contentService.deleteById(contentId)){
            throw new NotFoundException();
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
