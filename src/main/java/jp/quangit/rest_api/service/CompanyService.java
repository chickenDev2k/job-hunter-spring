package jp.quangit.rest_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jp.quangit.rest_api.domain.Company;
import jp.quangit.rest_api.domain.dto.ResultPaginationDTO;
import jp.quangit.rest_api.repository.CompanyRepository;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    // check compay isExited
    public boolean isExited(String name) {
        Company currentCompany = this.companyRepository.findByName(name);
        if (currentCompany == null) {
            return false;
        }
        return true;
    }

    public Company handleSaveCompany(Company company) {
        return this.companyRepository.save(company);
    }

    public Company getCompanyById(long id) {
        return this.companyRepository.findById(id);
    }

    public Company getCompanyByName(String name) {
        return this.companyRepository.findByName(name);
    }

    public ResultPaginationDTO getAllCompany(Specification<Company> specification, Pageable pageable) {
        ResultPaginationDTO rs = new ResultPaginationDTO();
        Page<Company> page = this.companyRepository.findAll(specification, pageable);
        ResultPaginationDTO.Meta mt = new ResultPaginationDTO.Meta();
        mt.setPage(pageable.getPageNumber() + 1);
        mt.setPageSize(pageable.getPageSize());
        mt.setPages(page.getTotalPages());
        mt.setTotal(page.getTotalElements());
        rs.setMeta(mt);
        rs.setResult(page.getContent());
        return rs;
    }

    public void deleteCompany(Company currentCompany) {
        this.companyRepository.delete(currentCompany);
    }

}
