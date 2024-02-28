package me.jazzy.librarymanagementsystem.repository;

import me.jazzy.librarymanagementsystem.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select distinct c from Category c where c.categoryName = :name")
    Optional<Category> findByCategoryName(@Param("name") String name);
}
