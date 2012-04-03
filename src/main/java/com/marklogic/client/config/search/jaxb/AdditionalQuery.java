//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-b10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.03.20 at 02:46:45 PM PDT 
//


/*
 * Copyright 2012 MarkLogic Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.marklogic.client.config.search.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class AdditionalQuery
    extends JAXBElement<AnyCtsElement>
{

    protected final static QName NAME = new QName("http://marklogic.com/appservices/search", "additional-query");

    public AdditionalQuery(AnyCtsElement value) {
        super(NAME, ((Class) AnyCtsElement.class), null, value);
    }

    public AdditionalQuery() {
        super(NAME, ((Class) AnyCtsElement.class), null, null);
    }

}