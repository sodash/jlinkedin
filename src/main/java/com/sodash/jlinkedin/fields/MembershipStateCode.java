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
 *     &lt;enumeration value="blocked"/>
 *     &lt;enumeration value="non-member"/>
 *     &lt;enumeration value="awaiting-confirmation"/>
 *     &lt;enumeration value="awaiting-parent-group-confirmation"/>
 *     &lt;enumeration value="member"/>
 *     &lt;enumeration value="moderator"/>
 *     &lt;enumeration value="manager"/>
 *     &lt;enumeration value="owner"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
public enum MembershipStateCode {

    
    BLOCKED("blocked"),
    
    NON_MEMBER("non-member"),
    
    AWAITING_CONFIRMATION("awaiting-confirmation"),
    
    AWAITING_PARENT_GROUP_CONFIRMATION("awaiting-parent-group-confirmation"),
    
    MEMBER("member"),
    
    MODERATOR("moderator"),
    
    MANAGER("manager"),
    
    OWNER("owner");
    private final String value;

    MembershipStateCode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MembershipStateCode fromValue(String v) {
        for (MembershipStateCode c: MembershipStateCode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
