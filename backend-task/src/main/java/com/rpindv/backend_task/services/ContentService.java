package com.rpindv.backend_task.services;

import com.rpindv.backend_task.entities.Content;
import com.rpindv.backend_task.helpers.FileNameSanitization;
import com.rpindv.backend_task.helpers.FileUpload;
import com.rpindv.backend_task.helpers.validators.NotFoundException;
import com.rpindv.backend_task.models.ContentDTO;
import com.rpindv.backend_task.repositories.CategoryContentRepository;
import com.rpindv.backend_task.repositories.ContentRepository;
import com.rpindv.backend_task.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContentService {
    private final ContentRepository contentRepository;
    private final CategoryContentRepository categoryContentRepository;
    private final UserRepository userRepository;

    @Value("${file.storage.path}")
    private String uploadDirectory;

    @Transactional(rollbackOn = Exception.class)
    public void createContent(MultipartFile file, String title, String description, Integer id_category_content, Integer id_user) throws IOException {
        String path = uploadDirectory + "/" + id_user + "/";

        String sanitizedFileName = FileNameSanitization.sanitizeFileName(FilenameUtils.getName(file.getOriginalFilename()));
        String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        Content content = Content.builder()
                .title(title)
                .description(description)
                .content_url(path + sanitizedFileName)
                .content_path(path)
                .content_name(sanitizedFileName)
                .content_extension(fileExtension)
                .content_type(file.getContentType())
                .id_category_content(categoryContentRepository.findById(id_category_content).orElseThrow(() -> new RuntimeException("Category Content not found")))
                .created_by(userRepository.findById(id_user).orElseThrow(() -> new RuntimeException("User not found")))
                .created_at(LocalDateTime.now())
                .build();

        String thumbnailURL = FileUpload.upload(path, uploadDirectory, file, sanitizedFileName, id_user);

        content.setThumbnail_url(thumbnailURL);
        contentRepository.save(content);
    }

    @Transactional(rollbackOn = Exception.class)
    public Boolean deleteById(Long contentId) {
        Content content = contentRepository.findById(contentId).orElseThrow(NotFoundException::new);
        try {
            Files.deleteIfExists(Paths.get(content.getContent_url()));
            Files.deleteIfExists(Paths.get(content.getThumbnail_url()));
        }
        catch (NoSuchFileException e) {
            throw new RuntimeException("No such file/directory");
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong, try again later");
        }

        contentRepository.deleteById(contentId);
        return true;
    }

    @Transactional(rollbackOn = Exception.class)
    public void updateContentById(Long contentId, ContentDTO content) {
        Optional<Content> existing = contentRepository.findById(contentId);
        existing.get().setTitle(content.getTitle());
        existing.get().setDescription(content.getDescription());
        existing.get().setId_category_content(categoryContentRepository.findById(content.getCategory()).orElseThrow(() -> new RuntimeException("Category Content not found")));
        contentRepository.save(existing.get());
    }

    public Optional<Content> getContentById(Long id) {
        return contentRepository.findById(id);
    }
}
