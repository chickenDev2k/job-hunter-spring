package jp.quangit.rest_api.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import jp.quangit.rest_api.domain.dto.Company;
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

    public Optional<Company> getCompanyById(Company company) {
        return this.companyRepository.findById(company.getId());
    }

    public Company getCompanyByName(String name) {
        return this.companyRepository.findByName(name);
    }

}
