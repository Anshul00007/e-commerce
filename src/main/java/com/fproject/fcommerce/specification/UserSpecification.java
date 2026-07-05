package com.fproject.fcommerce.specification;

import org.springframework.data.jpa.domain.Specification;

import com.fproject.fcommerce.entity.User;

public class UserSpecification {
    public static Specification<User> hasName(String name) {

        if (name == null || name.isBlank()) {
            return null;
        }

        return (root, query, cb) -> cb.equal(root.get("name"), name);
    }

    public static Specification<User> isEnabled(Boolean enabled) {
        if (enabled == null) {
            return null;
        }
        return (root, query, cb) -> cb.equal(root.get("enabled"), enabled);
    }

    public static Specification<User> hasRole(String role) {
        if (role == null || role.isBlank())
            return null;
        return (root, query, cb) -> cb.equal(root.join("roles").get("name"), role);
    }

}
