package com.ezen.mood.dto;

import com.ezen.mood.domain.content.category.Category;
import com.ezen.mood.domain.content.company.Company;
import com.ezen.mood.domain.content.genre.Genre;
import lombok.Data;

@Data
public class GccDto {
    private Long id;
    private String engname;
    private String krname;

    public static Genre toGenreEntity(GccDto dto) {
        return Genre.builder()
                .id(dto.getId())
                .engname(dto.getEngname())
                .krname(dto.getKrname())
                .build();
    }
    public static Category toCategoryEntity(GccDto dto) {
        return Category.builder()
                .id(dto.getId())
                .engname(dto.getEngname())
                .krname(dto.getKrname())
                .build();
    }
    public static Company toCompanyEntity(GccDto dto) {
        return Company.builder()
                .id(dto.getId())
                .engname(dto.getEngname())
                .krname(dto.getKrname())
                .build();
    }
}
