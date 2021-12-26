package com.ezen.mood.service;

import com.ezen.mood.domain.member.Member;
import com.ezen.mood.domain.posts.Posts;
import com.ezen.mood.dto.PostFormDto;
import com.ezen.mood.repository.FilesRepository;
import com.ezen.mood.repository.MemberRepository;
import com.ezen.mood.repository.PostsRepository;
import com.ezen.mood.util.FileUtilities;
import com.ezen.mood.util.Files;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
@Transactional
@Service
public class PostsService {

    private final PostsRepository postsRepository;
    private final FilesRepository filesRepository;
    private final MemberRepository memberRepository;
    private final FileUtilities fileUtilities;

    public Posts save(PostFormDto postFormDto, String email) throws IOException {
        Member member = memberRepository.findByEmail(email).get();

//        포스트 등록
        Posts post = postFormDto.toEntity();
        post.setMember(member);

        Posts save = postsRepository.save(post);

        List<Files> files = fileUtilities.storeFiles(postFormDto.getImageFiles(), post, filesRepository);

//        포스터에 컨텐츠 등록 해줘야함.
        post.setFiles(files);

        return save;
    }


    @Transactional(readOnly = true)
    public List<Posts> findAll() {
        return postsRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Posts findById(Long id) {
        return postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));
    }

    @Transactional(readOnly = true)
    public List<Posts> findAllDesc() {
        return postsRepository.findAllByOrderByIdDesc();
    }

    public void deletePost(Long id) {
        Posts post = postsRepository.findById(id).get();
        List<Files> files = post.getFiles();
        for (Files file : files) {
            file.deletePhysicFile();
        }
        postsRepository.delete(post);

    }

    public void updatePost(Long id, String email, PostFormDto postFormDto) throws Exception {
        Member member = memberRepository.findByEmail(email).get();
        Posts post = postsRepository.findById(id).orElse(null);
        if (!post.getMember().getEmail().equals(email)) {
            throw new IllegalArgumentException("게시글의 소유자가 아닙니다");
        }

        List<Files> files = fileUtilities.storeFiles(postFormDto.getImageFiles(), post, filesRepository);

        post.setContent(postFormDto.getContent());
        post.setTitle(postFormDto.getTitle());
        post.setFiles(files);
    }
}
