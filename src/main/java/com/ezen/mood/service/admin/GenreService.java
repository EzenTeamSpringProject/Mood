package com.ezen.mood.service.admin;

import com.ezen.mood.domain.content.genre.Genre;
import com.ezen.mood.dto.GccDto;
import com.ezen.mood.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GenreService {

    private final GenreRepository genreRepository;

    /**
     *  Genre CRUD
     */
    public Genre saveGenre(GccDto gccDto) {
        Genre newGenre = GccDto.toGenreEntity(gccDto);
        genreRepository.save(newGenre);
        return genreRepository.findById(newGenre.getId()).orElse(null);
    }

    public List<Genre> loadGenreList() {
        List<Genre> list = genreRepository.findAll();
        return list;
    }

    public Genre loadGenre(Long id) {
        return genreRepository.findById(id).orElse(null);
    }

    public void removeGenre(Long id) {
        genreRepository.deleteById(id);
    }

    // 저장메소드랑 수정메소드랑 if 분기점을 통해서 결합할 수도 있긴 하겠다.
    public void modifyGenre(GccDto gccDto) {
        Genre newEntity = GccDto.toGenreEntity(gccDto);

        Optional<Genre> original = genreRepository.findById(newEntity.getId());

        original.ifPresent(genre ->{
            genre.setEngname(newEntity.getEngname());
            genre.setKrname(newEntity.getKrname());
        });
    }
}
