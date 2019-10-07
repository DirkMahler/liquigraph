/*
 * Copyright 2014-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.liquigraph.core.io.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.dom.DOMSource;

public class DomSourceValidatorFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(DomSourceValidatorFactory.class);
    private final SchemaDetector schemaDetector;

    public DomSourceValidatorFactory() {
        schemaDetector = new SchemaDetector();
    }

    public DomSourceValidator createValidator(DOMSource source) {
        if (schemaDetector.hasExplicitSchema(source)) {
            LOGGER.debug("Explicit schema detected: validation against schema to start");
            return new ExplicitSchemaValidator();
        }
        LOGGER.debug("No schema detected: validation against embedded schema to start");
        return new ImplicitSchemaValidator();
    }
}
