package jp.quangit.rest_api.utils;

import jp.quangit.rest_api.domain.dto.Company;
import jp.quangit.rest_api.domain.dto.CompanyDTO;

public class ConvertFromDTO {
    public static Company convertToCompanyObj(CompanyDTO companyDTO) {
        Company company = new Company();
        company.setName(companyDTO.getName());
        company.setDescription(companyDTO.getDescription());
        company.setAddress(companyDTO.getAddress());
        company.setLogo(companyDTO.getLogo());

        return company;
    }

}
