package com.rpindv.backend_task.helpers;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.jcodec.api.FrameGrab;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Paths;

import static com.rpindv.backend_task.helpers.FileProcessing.removeFileExtension;

public class FileUpload {
    public static String upload(String path, String uploadDirectory, MultipartFile file, String fileName, Integer id_user ) throws IOException {
        File directory = new File(path);
        directory.mkdirs();

        String projectDir = new File(".").getCanonicalPath();

        String uploadPath = Paths.get(projectDir, uploadDirectory).toString();

        file.transferTo(new File(uploadPath + File.separator + id_user + File.separator + fileName));

//        Get the file bytes and save it
//        byte[] bytes = file.getBytes();
//        Path path = Paths.get(uploadDirectory + File.separator + file.getOriginalFilename());
//        Files.write(path, bytes);

        String type = file.getContentType().split("/")[0];
        StringBuilder thumbnail = new StringBuilder();

        if(type.equals("image")){
            Thumbnails.of(new File(directory, fileName))
                    .size(150, 150)
                    .toFiles(directory, Rename.SUFFIX_HYPHEN_THUMBNAIL);
            thumbnail.append(path).append(removeFileExtension(file.getOriginalFilename(), false)).append("-thumbnail").append(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
        } else if(type.equals("video")) {
            int frameNumber = 0;
            try {
                Picture picture = FrameGrab.getFrameFromFile(
                        new File(directory, fileName), frameNumber);
                BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                Thumbnails.of(bufferedImage)
                        .size(300, 300)
                        .outputFormat("png")
                        .outputQuality(1)
                        .toOutputStream(outputStream);

                OutputStream outputFile = new FileOutputStream(directory + File.separator + removeFileExtension(fileName, false) + "-thumbnail" + ".png");
                outputStream.writeTo(outputFile);

//                ImageIO.write(bufferedImage, "png", new File(
//                        directory + File.separator + removeFileExtension(file.getOriginalFilename(), true)  + ".png"));

//                Thumbnails.of(new File(directory + File.separator + removeFileExtension(file.getOriginalFilename(), true) + ".png"))
//                        .size(150, 150)
//                        .toFiles(directory, Rename.PREFIX_HYPHEN_THUMBNAIL);
                thumbnail.append(path).append(removeFileExtension(fileName, false)).append("-thumbnail").append(".png");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else {
            thumbnail.append("files/templates/").append("default-thumbnail.png");
        }

        return thumbnail.toString();
    }
}
