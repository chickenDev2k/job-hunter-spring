package jp.quangit.rest_api.repository;

import jp.quangit.rest_api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findById(long id);

}
