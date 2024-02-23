package me.jazzy.librarymanagementsystem.repository;

import me.jazzy.librarymanagementsystem.model.Penalty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PenaltyRepository extends JpaRepository<Penalty, Long> {
}
