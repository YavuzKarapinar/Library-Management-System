package me.jazzy.librarymanagementsystem.repository;

import me.jazzy.librarymanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
