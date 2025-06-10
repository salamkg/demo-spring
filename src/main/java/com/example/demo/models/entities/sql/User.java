package com.example.demo.models.entities.sql;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements Cloneable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private Date createdAt;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    public String toString() {
        return "User{"
            + "id=" + id
            + ", username='" + username + '\''
            + ", email='" + email + '\''
            + ", createdAt='" + createdAt + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(username, user.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
