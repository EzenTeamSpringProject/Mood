package com.ezen.mood.util;

import com.ezen.mood.domain.posts.Posts;
import com.ezen.mood.repository.FilesRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileUtilities {

    public final static String fileDir = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files\\";

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    public List<Files> storeFiles(List<MultipartFile> multipartFiles, Posts post, FilesRepository filesRepository) throws IOException {
        List<Files> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storeFileResult.add(storeFile(multipartFile,post,filesRepository));
            }
        }
        return storeFileResult;
    }

    public Files storeFile(MultipartFile multipartFile,Posts post,FilesRepository filesRepository) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }
        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));

        Files file = Files.builder()
                .orginalname(originalFilename)
                .filename(storeFileName)
                .filepath(getFullPath(storeFileName))
                .build();
        file.setPost(post);
//        Files saveFile = filesRepository.save(file);
//        return saveFile;
        return file;
    }

    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }


}
