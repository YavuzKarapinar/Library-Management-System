package me.jazzy.librarymanagementsystem.repository;

import me.jazzy.librarymanagementsystem.model.Penalty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenaltyRepository extends JpaRepository<Penalty, Long> {
}
