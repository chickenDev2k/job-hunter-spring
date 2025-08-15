package jp.quangit.rest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.quangit.rest_api.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    public User findById(long id);

    public User findByEmail(String userName);

    public boolean existsByEmail(String email);

    public User findByRefreshTokenAndEmail(String token, String email);

}
