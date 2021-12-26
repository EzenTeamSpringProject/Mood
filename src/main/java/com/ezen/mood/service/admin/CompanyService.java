package com.ezen.mood.service.admin;

import com.ezen.mood.domain.content.company.Company;
import com.ezen.mood.dto.GccDto;
import com.ezen.mood.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company saveCompany(GccDto gccDto) {
        Company entity = GccDto.toCompanyEntity(gccDto);
        companyRepository.save(entity);
        return companyRepository.findById(entity.getId()).orElse(null);
    }

    public List<Company> loadCompanyList() {
        List<Company> list = companyRepository.findAll();
        return list;
    }

    public Company loadCompany(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    public void removeCompany(Long id) {
        companyRepository.deleteById(id);
    }

    // 저장메소드랑 수정메소드랑 if 분기점을 통해서 결합할 수도 있긴 하겠다.
    public void modifyCompany(GccDto gccDto) {
        Company newEntity = GccDto.toCompanyEntity(gccDto);

        Optional<Company> original = companyRepository.findById(newEntity.getId());

        original.ifPresent(company ->{
            original.ifPresent(genre ->{
                genre.setEngname(newEntity.getEngname());
                genre.setKrname(newEntity.getKrname());
            });
        });
    }
}
