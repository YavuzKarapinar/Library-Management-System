package me.jazzy.librarymanagementsystem.service;

import lombok.AllArgsConstructor;
import me.jazzy.librarymanagementsystem.exception.notfound.CategoryNotFoundException;
import me.jazzy.librarymanagementsystem.model.Category;
import me.jazzy.librarymanagementsystem.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category findCategoryByName(String name) {
        return categoryRepository.findByCategoryName(name)
                .orElseThrow(() -> new CategoryNotFoundException("There is no such category"));
    }

}
