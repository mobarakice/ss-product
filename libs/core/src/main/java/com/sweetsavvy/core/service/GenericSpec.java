package com.sweetsavvy.core.service;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
@Getter
public class GenericSpec<E> {

    private final Specification<E> specs;

    private GenericSpec(GenericSpecBuilder<E> builder) {
        this.specs = builder.completeSpec;
    }

    public static class GenericSpecBuilder<E> {
        private Specification<E> completeSpec = Specification.where(null);

        public GenericSpecBuilder<E> equal(String key, Object value) {
            if (isValidValue(value)) {
                completeSpec = completeSpec.and((root, query, builder) -> builder.equal(root.get(key), value));
            }
            return this;
        }

        public GenericSpecBuilder<E> notEqual(String key, Object value) {
            if (isValidValue(value)) {
                completeSpec = completeSpec.and((root, query, builder) -> builder.notEqual(root.get(key), value));
            }
            return this;
        }

        public GenericSpecBuilder<E> greaterThan(String key, Object value) {
            if (isValidValue(value)) {
                completeSpec = completeSpec.and((root, query, builder) -> builder.greaterThan(root.get(key), value.toString()));
            }
            return this;
        }

        public GenericSpecBuilder<E> greaterThanOrEqual(String key, Object value) {
            if (isValidValue(value)) {
                completeSpec = completeSpec.and((root, query, builder) -> builder.greaterThanOrEqualTo(root.get(key), value.toString()));
            }
            return this;
        }

        public GenericSpecBuilder<E> lessThanOrEqual(String key, Object value) {
            if (isValidValue(value)) {
                completeSpec = completeSpec.and((root, query, builder) -> builder.lessThanOrEqualTo(root.get(key), value.toString()));
            }
            return this;
        }

        public GenericSpecBuilder<E> startWith(String key, Object value) {
            if (isValidValue(value)) {
                completeSpec = completeSpec.and((root, query, builder) -> builder.like(root.get(key), value + "%"));
            }
            return this;
        }

        public GenericSpecBuilder<E> endWith(String key, Object value) {
            if (isValidValue(value)) {
                completeSpec = completeSpec.and((root, query, builder) -> builder.like(root.get(key), "%" + value));
            }
            return this;
        }

        public GenericSpecBuilder<E> like(String key, Object value) {
            if (isValidValue(value)) {
                completeSpec = completeSpec.and((root, query, builder) -> builder.like(root.get(key), "%" + value + "%"));
            }
            return this;
        }

        public GenericSpecBuilder<E> or(Specification<E> otherSpec) {
            if (otherSpec != null) {
                completeSpec = completeSpec.or(otherSpec);
            }
            return this;
        }

        public GenericSpec<E> build() {
            return new GenericSpec<>(this);
        }

        private boolean isValidValue(Object value) {
            return value != null && (!(value instanceof String) || !StringUtils.isBlank((String) value));
        }
    }
}
