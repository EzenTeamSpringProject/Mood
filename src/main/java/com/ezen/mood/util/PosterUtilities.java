package com.ezen.mood.util;

import com.ezen.mood.domain.content.Content;
import com.ezen.mood.domain.content.poster.Poster;
import com.ezen.mood.domain.review.Review;
import com.ezen.mood.repository.PosterRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class PosterUtilities {

    public final static String fileDir = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files\\posters\\";

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    public List<Poster> storePosters(List<MultipartFile> multipartFiles, Content content,PosterRepository posterRepository) throws IOException {
        List<Poster> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storeFileResult.add(storePoster(multipartFile,content,posterRepository));
            }
        }
        return storeFileResult;
    }

    public Poster storePoster(MultipartFile multipartFile, Content content,PosterRepository posterRepository) throws IOException {
//        들어오는 멀티파트 파일이 비어있는지 체크
        if (multipartFile.isEmpty()) {
            return null;
        }
//        파일 원래 이름
        String originalFilename = multipartFile.getOriginalFilename();
//        원래이름 떼어내고 uuid로 생성한 새로운 이름을 붙여줌
        String storeFileName = createStoreFileName(originalFilename);
//        멀티파트 파일을  저장경로 + 파일이름 으로 전송
        multipartFile.transferTo(new File(getFullPath(storeFileName)));

//         poster 엔티티 생성
        Poster poster = Poster.builder()
                .orginalname(originalFilename)
                .filename(storeFileName)
                .filepath(getFullPath(storeFileName))
                .build();
//        엔티티에 컨텐츠 등록

        Poster savePoster = posterRepository.save(poster);
        return savePoster;
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

    public Poster storePosterForReview(MultipartFile multipartFile, Review review, PosterRepository posterRepository) throws IOException {
//        들어오는 멀티파트 파일이 비어있는지 체크
        if (multipartFile.isEmpty()) {
            return null;
        }
//        파일 원래 이름
        String originalFilename = multipartFile.getOriginalFilename();
//        원래이름 떼어내고 uuid로 생성한 새로운 이름을 붙여줌
        String storeFileName = createStoreFileName(originalFilename);
//        멀티파트 파일을  저장경로 + 파일이름 으로 전송
        multipartFile.transferTo(new File(getFullPath(storeFileName)));

//         poster 엔티티 생성
        Poster poster = Poster.builder()
                .orginalname(originalFilename)
                .filename(storeFileName)
                .filepath(getFullPath(storeFileName))
                .build();
//        엔티티에 컨텐츠 등록

        Poster savePoster = posterRepository.save(poster);
        return savePoster;
    }

    public String storePosterForMember(MultipartFile multipartFile) throws IOException {

        if (multipartFile.isEmpty()) {
            return null;
        }
        String originalFilename = multipartFile.getOriginalFilename();

        String storeFileName = createStoreFileName(originalFilename);

        multipartFile.transferTo(new File(getFullPath(storeFileName)));

        return getFullPath(storeFileName);
    }

}
