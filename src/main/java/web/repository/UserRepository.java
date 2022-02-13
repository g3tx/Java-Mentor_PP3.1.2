package web.repository;


import web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = :username")
    User getUserByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.user_id = :id")
    User getUserById(Long id);
}
