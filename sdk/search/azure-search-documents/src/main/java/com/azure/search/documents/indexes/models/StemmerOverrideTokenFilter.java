// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.search.documents.indexes.models;

import com.azure.core.rest.annotation.Fluent;
import com.azure.core.serde.SerdeProperty;
import com.azure.core.serde.SerdeTypeInfo;
import com.azure.core.serde.SerdeTypeName;
import java.util.List;

/**
 * Provides the ability to override other stemming filters with custom
 * dictionary-based stemming. Any dictionary-stemmed terms will be marked as
 * keywords so that they will not be stemmed with stemmers down the chain. Must
 * be placed before any stemming filters. This token filter is implemented
 * using Apache Lucene.
 */
@SerdeTypeInfo(use = SerdeTypeInfo.Id.NAME, include = SerdeTypeInfo.As.PROPERTY, property = "@odata.type")
@SerdeTypeName("#Microsoft.Azure.Search.StemmerOverrideTokenFilter")
@Fluent
public final class StemmerOverrideTokenFilter extends TokenFilter {
    /*
     * A list of stemming rules in the following format: "word => stem", for
     * example: "ran => run".
     */
    @SerdeProperty(value = "rules")
    private List<String> rules;

    /**
     * Constructor of {@link StemmerOverrideTokenFilter}.
     *
     * @param name The name of the token filter. It must only contain letters, digits,
     * spaces, dashes or underscores, can only start and end with alphanumeric
     * characters, and is limited to 128 characters.
     * @param rules A list of stemming rules in the following format: "word =%3E stem", for
     * example: "ran =%3E run".
     */
    public StemmerOverrideTokenFilter(String name, List<String> rules) {
        super(name);
        this.rules = rules;
    }

    /**
     * Get the rules property: A list of stemming rules in the following
     * format: "word =&gt; stem", for example: "ran =&gt; run".
     *
     * @return the rules value.
     */
    public List<String> getRules() {
        return this.rules;
    }

}