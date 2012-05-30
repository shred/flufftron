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
package org.shredzone.flufftron.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.shredzone.flufftron.model.Fluff;
import org.shredzone.flufftron.model.Person;
import org.shredzone.flufftron.repository.PersonDao;
import org.springframework.stereotype.Service;

/**
 * Services for fluffs.
 *
 * @author Richard "Shred" Körber
 */
@Service
public class FluffService {

    private @Resource PersonDao personDao;

    /**
     * Collects a map of person's {@link ObjectId} and {@link Person} objects for all
     * persons used in the given collection of {@link Fluff}.
     *
     * @param fluffs
     *            {@link Fluff} to find all {@link Person} for
     * @return Map of {@link ObjectId} and {@link Person}
     */
    public Map<ObjectId, Person> getPersonMap(Collection<Fluff> fluffs) {
        Map<ObjectId, Person> result = new HashMap<ObjectId, Person>();

        for (Fluff fluff : fluffs) {
            ObjectId personId = fluff.getPersonId();
            if (!result.containsKey(personId)) {
                Person person = personDao.findById(personId);
                result.put(personId, person);
            }
        }

        return result;
    }

}
