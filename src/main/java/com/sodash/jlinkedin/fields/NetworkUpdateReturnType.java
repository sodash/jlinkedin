/*
 * Copyright 2010-2011 Nabeel Mukhtar 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *  http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 * 
 */

package com.sodash.jlinkedin.fields;



/**
 * <p>Java class for null.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType>
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ANSW"/>
 *     &lt;enumeration value="APPM"/>
 *     &lt;enumeration value="APPS"/>
 *     &lt;enumeration value="CONN"/>
 *     &lt;enumeration value="NCON"/>
 *     &lt;enumeration value="CCEM"/>
 *     &lt;enumeration value="JOBP"/>
 *     &lt;enumeration value="JGRP"/>
 *     &lt;enumeration value="PICU"/>
 *     &lt;enumeration value="PREC"/>
 *     &lt;enumeration value="PROF"/>
 *     &lt;enumeration value="QSTN"/>
 *     &lt;enumeration value="STAT"/>
 *     &lt;enumeration value="PRFX"/>
 *     &lt;enumeration value="SHAR"/>
 *     &lt;enumeration value="CMPY"/>
 *     &lt;enumeration value="VIRL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
public enum NetworkUpdateReturnType {

    
    ANSWER_UPDATE("ANSW"),
    
    APPLICATION_CONNECTION_UPDATE("APPM"),
    
    APPLICATION_TO_MEMBER_UPDATE("APPS"),
    
    CONNECTION_ADDED_CONNECTIONS("CONN"),
    
    NEW_CONNECTIONS("NCON"),
    
    CONTACT_JOINED("CCEM"),
    
    JOB_POSTED("JOBP"),
    
    CONNECTION_JOINED_GROUP("JGRP"),
    
    CONNECTION_UPDATED_PICTURE("PICU"),
    
    CONNECTION_RECOMMENDED("PREC"),
    
    CONNECTION_UPDATED_PROFILE("PROF"),
    
    QUESTION_UPDATED("QSTN"),
    
    STATUS_UPDATED("STAT"),
    
    EXTENDED_PROFILE_UPDATED("PRFX"),
    
    SHARED_ITEM("SHAR"),
    
    COMPANY_UPDATED("CMPY"),
    
    VIRAL_UPDATE("VIRL");
    private final String value;

    NetworkUpdateReturnType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static NetworkUpdateReturnType fromValue(String v) {
        for (NetworkUpdateReturnType c: NetworkUpdateReturnType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
