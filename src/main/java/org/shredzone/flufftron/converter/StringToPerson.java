/*
 * flufftron - Fluff Management System
 *
 * Copyright (C) 2012 Richard "Shred" Körber
 *   http://flufftron.shredzone.org
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.shredzone.flufftron.converter;

import javax.annotation.Resource;

import org.shredzone.flufftron.model.Person;
import org.shredzone.flufftron.repository.PersonDao;
import org.springframework.core.convert.converter.Converter;

/**
 * Converts a string containing a person's nick to a {@link Person} object.
 *
 * @author Richard "Shred" Körber
 */
public class StringToPerson implements Converter<String, Person> {

    private @Resource PersonDao personDao;

    @Override
    public Person convert(String string) {
        return personDao.findByNick(string);
    }

}
