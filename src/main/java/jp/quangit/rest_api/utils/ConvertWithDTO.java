package jp.quangit.rest_api.utils;

import jp.quangit.rest_api.domain.Company;
import jp.quangit.rest_api.domain.User;
import jp.quangit.rest_api.domain.dto.CompanyDTO;
import jp.quangit.rest_api.domain.dto.UserCreatedDTO;
import jp.quangit.rest_api.domain.dto.UserDTO;
import jp.quangit.rest_api.domain.dto.UserUpdatedDTO;

public class ConvertWithDTO {
    public static Company convertToCompanyObj(CompanyDTO companyDTO) {
        Company company = new Company();
        company.setName(companyDTO.getName());
        company.setDescription(companyDTO.getDescription());
        company.setAddress(companyDTO.getAddress());
        company.setLogo(companyDTO.getLogo());

        return company;
    }

    public static UserCreatedDTO convertToUserCreatedDTO(User user) {
        UserCreatedDTO userResDTO = new UserCreatedDTO();
        userResDTO.setId(user.getId());
        userResDTO.setName(user.getName());
        userResDTO.setEmail(user.getEmail());
        userResDTO.setGender(user.getGender());
        userResDTO.setAddress(user.getAddress());
        userResDTO.setAge(user.getAge());
        userResDTO.setCreatedAt(user.getCreatedAt());
        return userResDTO;
    }

    public static UserUpdatedDTO convertToUserUpdatedDTO(User user) {
        UserUpdatedDTO userUpdated = new UserUpdatedDTO();
        userUpdated.setId(user.getId());
        userUpdated.setName(user.getName());
        userUpdated.setGender(user.getGender());
        userUpdated.setAddress(user.getAddress());
        userUpdated.setAge(user.getAge());
        userUpdated.setUpdatedAt(user.getUpdatedAt());
        return userUpdated;
    }

    public static UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setGender(user.getGender());
        userDTO.setAddress(user.getAddress());
        userDTO.setAge(user.getAge());
        userDTO.setUpdatedAt(user.getUpdatedAt());
        userDTO.setCreatedAt(user.getCreatedAt());
        return userDTO;
    }

}
