package jp.quangit.rest_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkraft.springfilter.boot.Filter;

import jakarta.validation.Valid;
import jp.quangit.rest_api.domain.Company;
import jp.quangit.rest_api.domain.RestResponse;
import jp.quangit.rest_api.domain.dto.CompanyDTO;
import jp.quangit.rest_api.domain.dto.ResultPaginationDTO;
import jp.quangit.rest_api.service.CompanyService;
import jp.quangit.rest_api.utils.ConvertWithDTO;
import jp.quangit.rest_api.utils.annotation.ApiMessage;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1")
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

    // get company list
    @GetMapping("/companies")

    @ApiMessage("fetch all company")
    public ResponseEntity<ResultPaginationDTO> getAllCompany(
            @Filter Specification<Company> specification, Pageable pageable) {

        return ResponseEntity.ok().body(this.companyService.getAllCompany(specification, pageable));

    }

    // update company success status code 200 with updatedBy , updatedAt use
    // @PreUpdate
    @PutMapping("companies")
    public ResponseEntity<Company> updateCompany(@RequestBody Company updateCompany) {
        Company currentCompany = this.companyService.getCompanyById(updateCompany.getId());
        if (currentCompany != null) {
            currentCompany.setName(updateCompany.getName());
            currentCompany.setDescription(updateCompany.getDescription());
            currentCompany.setAddress(updateCompany.getAddress());
            currentCompany.setLogo(updateCompany.getLogo());
            currentCompany.handleBeforeUpdate();
        }
        return ResponseEntity.status(HttpStatus.OK.value()).body(this.companyService.handleSaveCompany(currentCompany));

    }

    // delete company
    @DeleteMapping("companies/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable("id") long id) {
        Company currentCompany = this.companyService.getCompanyById(id);
        if (currentCompany != null) {
            this.companyService.deleteCompany(currentCompany);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
