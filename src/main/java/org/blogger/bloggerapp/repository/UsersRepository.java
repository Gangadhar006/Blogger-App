package org.blogger.bloggerapp.repository;

import org.blogger.bloggerapp.entity.Tags;
import org.blogger.bloggerapp.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}