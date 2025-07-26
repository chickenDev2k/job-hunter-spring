package jp.quangit.rest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.quangit.rest_api.domain.dto.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findById(long id);

    public User findByEmail(String userName);

}
