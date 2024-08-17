package com.prueba.personaltraining.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
public class UserEntity implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @Column(name = "last_name", length = 45)
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password", nullable = false, length = 32)
    private String password;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

    @Column(name = "dni", nullable = false, length = 9)
    private String dni;

    @Column(name = "status", nullable = false, length = 12)
    private String status;

    @ManyToOne()
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
    }

    @Override
    public String getUsername() {
        return dni;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}