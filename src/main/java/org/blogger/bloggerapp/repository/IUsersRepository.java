package org.blogger.bloggerapp.repository;

import org.blogger.bloggerapp.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
}