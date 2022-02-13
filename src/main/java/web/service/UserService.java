package web.service;

import web.model.Role;
import web.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    void saveUser(User user);

    void editUser(User user);

    Set<Role> ListOfRolesToSet(List<String> rolesId);

    boolean deleteUser(Long id);

    User getUserById(Long id);

    List<User> getAllUsers();
}
