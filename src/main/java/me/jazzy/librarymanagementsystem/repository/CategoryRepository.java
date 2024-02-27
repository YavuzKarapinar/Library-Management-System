package me.jazzy.librarymanagementsystem.repository;

import me.jazzy.librarymanagementsystem.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select distinct c from Category c where c.categoryName = :name")
    Optional<Category> findByCategoryName(@Param("name") String name);
}
