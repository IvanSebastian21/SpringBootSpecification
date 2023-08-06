package com.jpa.relation.persistence.specification;

import com.jpa.relation.persistence.entity.Category;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchCategorySpecification implements Specification<Category> {
    private String name;

    @Override
    public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(name)) {
            Expression<String> categoryNameToLowerCase = criteriaBuilder.lower(root.get("name"));
            predicates.add(criteriaBuilder.like(categoryNameToLowerCase, "%" + name.toLowerCase() + "%"));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
