package web.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "t_roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long role_id;

    private String rolename;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role() {
    }

    public Role(Long role_id, String rolename) {
        this.role_id = role_id;
        this.rolename = rolename;
    }

    public Long getRoleId() {
        return role_id;
    }

    public void setRoleId(Long id) {
        this.role_id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String name) {
        this.rolename = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return getRolename();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(role_id, role.role_id) &&
                Objects.equals(rolename, role.rolename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role_id, rolename);
    }

    @Override
    public String toString() {
        return rolename;
    }

    //убираем приставку ROLE (нужно для отображения в таблице)
    public String getRolenameWithoutSuf() {
        return rolename.replace("ROLE_", "");
    }
}
