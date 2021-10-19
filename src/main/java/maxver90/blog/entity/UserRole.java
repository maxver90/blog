package maxver90.blog.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "user_roles")
public class UserRole{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "userRole")
    private List<User> users;

    private String name;

    @Column(name = "display_name")
    private String displayName;

}
