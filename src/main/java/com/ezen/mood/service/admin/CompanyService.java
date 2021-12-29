package com.ezen.mood.service.admin;

import com.ezen.mood.domain.content.company.Company;
import com.ezen.mood.domain.content.company.ContentCompany;
import com.ezen.mood.dto.GccDto;
import com.ezen.mood.repository.CompanyRepository;
import com.ezen.mood.repository.ContentCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Transactional
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ContentCompanyRepository contentCompanyRepository;

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
        Company company = companyRepository.findById(id).orElse(null);
        List<ContentCompany> contents = company.getContents();
        for (ContentCompany content : contents) {
            if(content!=null){
                contentCompanyRepository.delete(content);
            }
        }

        companyRepository.deleteById(id);
    }

    // 저장메소드랑 수정메소드랑 if 분기점을 통해서 결합할 수도 있긴 하겠다.
    public void modifyCompany(GccDto gccDto) {
        Company newEntity = GccDto.toCompanyEntity(gccDto);

        Optional<Company> original = companyRepository.findById(newEntity.getId());

        original.ifPresent(company ->{
                company.setEngname(newEntity.getEngname());
                company.setKrname(newEntity.getKrname());
        });
    }
}
