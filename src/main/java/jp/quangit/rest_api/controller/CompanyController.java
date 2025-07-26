package jp.quangit.rest_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jp.quangit.rest_api.domain.RestResponse;
import jp.quangit.rest_api.domain.dto.Company;
import jp.quangit.rest_api.domain.dto.CompanyDTO;
import jp.quangit.rest_api.service.CompanyService;
import jp.quangit.rest_api.utils.ConvertFromDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // create company
    // @PostMapping("/companies")
    // public RestResponse<Company> postMethodName(@Valid @RequestBody CompanyDTO
    // reqCompanyDTO) {

    // Company company = ConvertFromDTO.convertToCompanyObj(reqCompanyDTO);
    // company = this.companyService.handleSaveCompany(company);
    // RestResponse res = new RestResponse<Company>();
    // res.setStatusCode(HttpStatus.CREATED.value());
    // res.setData(this.companyService.getCompanyById(company));
    // return res;
    // }

    @PostMapping("/companies")
    public ResponseEntity<Company> createCompany(@Valid @RequestBody Company reqCompany) {
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(this.companyService.handleSaveCompany(reqCompany));
    }

}
