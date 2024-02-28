package me.jazzy.librarymanagementsystem.repository;

import me.jazzy.librarymanagementsystem.model.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
}
