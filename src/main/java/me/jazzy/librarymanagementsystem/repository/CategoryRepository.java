package me.jazzy.librarymanagementsystem.repository;

import me.jazzy.librarymanagementsystem.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select distinct c.id, c.categoryName, c.categoryDefinition from Category c where c.categoryName =: name")
    Optional<Category> findByCategoryName(String name);
}
