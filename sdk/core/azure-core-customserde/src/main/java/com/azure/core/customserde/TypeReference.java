// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.core.customserde;

import com.azure.android.core.logging.ClientLogger;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a generic Java type, retaining information about generics.
 *
 * @param <T> The type being represented.
 */
public abstract class TypeReference<T> {
    private static final ClientLogger LOGGER = new ClientLogger(TypeReference.class);
    private static final String MISSING_TYPE = "Type constructed without type information.";

    private static final Map<Class<?>, TypeReference<?>> CACHE = new HashMap<>();
    private static final Object CACHE_LOCK = new Object();

    private final Type javaType;

    /**
     * Constructs a new {@link TypeReference} which maintains generic information.
     *
     * @throws IllegalArgumentException If the reference is constructed without type information.
     */
    public TypeReference() {
        Type superClass = this.getClass().getGenericSuperclass();
        if (superClass instanceof Class) {
            throw LOGGER.logExceptionAsError(new IllegalArgumentException(MISSING_TYPE));
        } else {
            this.javaType = ((ParameterizedType) superClass).getActualTypeArguments()[0];
        }
    }

    private TypeReference(Class<T> clazz) {
        this.javaType = clazz;
    }

    /**
     * Returns the {@link Type} representing {@code T}.
     *
     * @return The {@link Type} representing {@code T}.
     */
    public Type getJavaType() {
        return javaType;
    }

    /**
     * Creates and instance of {@link TypeReference} which maintains the generic {@code T} of the passed {@link Class}.
     * <p>
     * This method will cache the instance of {@link TypeReference} using the passed {@link Class} as the key. This is
     * meant to be used with non-generic types such as primitive object types and POJOs, not {@code Map<String, Object>}
     * or {@code List<Integer>} parameterized types.
     *
     * @param clazz {@link Class} that contains generic information used to create the {@link TypeReference}.
     * @param <T> The generic type.
     * @return Either the cached or new instance of {@link TypeReference}.
     */
    @SuppressWarnings("unchecked")
    public static <T> TypeReference<T> createInstance(Class<T> clazz) {
        synchronized (CACHE_LOCK) {
            TypeReference<T> typeReference = (TypeReference<T>) CACHE.get(clazz);
            if (typeReference == null) {
                typeReference = new TypeReference<T>(clazz) { };
                CACHE.put(clazz, typeReference);
            }
            return typeReference;
        }
    }
}