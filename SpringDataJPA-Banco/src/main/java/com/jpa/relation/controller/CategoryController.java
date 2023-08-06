package com.jpa.relation.controller;

import com.jpa.relation.persistence.entity.Category;
import com.jpa.relation.persistence.repository.CategoryRepository;
import com.jpa.relation.persistence.specification.SearchCategorySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("v1")
    public Page<Category> findAll(@PageableDefault(page = 0, size = 5) Pageable pageable,
                                  @RequestParam(required = false) String name) {
        System.out.println("categories/v1");

        SearchCategorySpecification categorySpecification = new SearchCategorySpecification(name);

        return categoryRepository.findAll(categorySpecification, pageable);
    }
}
