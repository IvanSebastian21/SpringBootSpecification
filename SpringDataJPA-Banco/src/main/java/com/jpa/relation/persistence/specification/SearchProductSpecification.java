package com.jpa.relation.persistence.specification;

import com.jpa.relation.persistence.entity.Category;
import com.jpa.relation.persistence.entity.Product;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Especificación que define los criterios de búsqueda para la entidad {@link Product}.
 * <p>
 * Esta especificación implementa la interfaz {@link Specification} de Spring Data JPA y proporciona
 * predicados para realizar búsquedas dinámicas de productos basadas en diferentes criterios, como el nombre,
 * el rango de precios y la categoría.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchProductSpecification implements Specification<Product> {

    private String name;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String category;

    /**
     * Crea un predicado de búsqueda para la entidad {@link Product} utilizando los criterios especificados.
     *
     * @param root            La raíz de la consulta, que representa la entidad Product.
     * @param query           La consulta actual de Criteria.
     * @param criteriaBuilder El constructor de criterios de JPA para construir predicados.
     * @return El predicado que representa los criterios de búsqueda especificados.
     */

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicateList = new ArrayList<>();

        // Agregar criterio de búsqueda por nombre
        if (StringUtils.hasText(name)) {
            Expression<String> productNameToLowerCase = criteriaBuilder.lower(root.get("name"));
            Predicate nameLikePredicate = criteriaBuilder.like(productNameToLowerCase, "%" + name.toLowerCase() + ("%"));
            predicateList.add(nameLikePredicate);
        }
        // Agregar criterio de búsqueda por precio mínimo
        if (minPrice != null && !minPrice.equals(BigDecimal.ZERO)) {
            Predicate priceGreaterThanEqualsPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
            predicateList.add(priceGreaterThanEqualsPredicate);

        }
        // Agregar criterio de búsqueda por precio máximo
        if (maxPrice != null && !maxPrice.equals(BigDecimal.ZERO)) {
            Predicate priceLessThanEqualPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
            predicateList.add(priceLessThanEqualPredicate);
        }
        // Realizar un join con la entidad Category y agregar criterio de búsqueda por nombre de categoría
        Join<Product, Category> productCategoryJoin = root.join("category");
        if (StringUtils.hasText(category)) {
            Expression<String> categoryNameToLowerCase = criteriaBuilder.lower(productCategoryJoin.get("name"));
            Predicate categoryNameLikePredicate = criteriaBuilder.like(categoryNameToLowerCase, "%".concat(category.toLowerCase()).concat("%"));
            predicateList.add(categoryNameLikePredicate);
        }

//        query.orderBy(criteriaBuilder.asc(root.get("price")));

        // Combinar todos los predicados con un operador AND y devolver el predicado final
        return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
    }
}
