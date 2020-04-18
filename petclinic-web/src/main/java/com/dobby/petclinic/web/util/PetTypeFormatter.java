/*
 * Copyright 2002-2013 the original author or authors.
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
package com.dobby.petclinic.web.util;


import com.dobby.petclinic.domain.PetType;
import com.dobby.petclinic.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

/**
 * 类型格式化
 */
@Component
public class PetTypeFormatter implements Formatter<PetType> {

    @Autowired
    private ClinicService clinicService;


    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        Collection<PetType> findPetTypes = this.clinicService.findPetTypes();
        for (PetType type : findPetTypes) {
            if (type.getName().equals(text)) {
                return type;
            }
        }
        throw new ParseException("type not found: " + text, 0);
    }

}
