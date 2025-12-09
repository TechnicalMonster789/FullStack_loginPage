package com.example.LoginPage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.LoginPage.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);   // 🔹 add this
}
