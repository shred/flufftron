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
package org.shredzone.flufftron.repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.shredzone.flufftron.model.Fluff;
import org.shredzone.flufftron.model.Person;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository of {@link Fluff} objects.
 *
 * @author Richard "Shred" Körber
 */
@Repository
public class FluffDao implements BaseDao<Fluff> {

    private @Resource MongoOperations mongoOperations;

    @Override
    public Fluff findById(ObjectId id) {
        return mongoOperations.findById(id, Fluff.class);
    }

    @Override
    public void save(Fluff entry) {
        mongoOperations.save(entry);
    }

    @Override
    public void remove(Fluff entry) {
        mongoOperations.remove(entry);
    }

    /**
     * Finds all {@link Fluff} objects. They are ordered by their creation date,
     * descendingly.
     *
     * @param first
     *            First entry, starting with 0
     * @param max
     *            Maximum number of entries, or 0 for all
     * @return List of {@link Fluff}
     */
    public List<Fluff> findAll(int first, int max) {
        Query q = new Query();

        if (first > 0) {
            q.skip(first);
        }

        if (max > 0) {
            q.limit(max);
        }

        q.sort().on("created", Order.DESCENDING);
        return mongoOperations.find(q, Fluff.class);
    }

    /**
     * Finds all {@link Fluff} objects of a {@link Person}. They are ordered by their
     * creation date, descendingly.
     *
     * @param person
     *            {@link Person} to find all {@link Fluff} for
     * @param first
     *            First entry, starting with 0
     * @param max
     *            Maximum number of entries, or 0 for all
     * @return List of {@link Fluff}
     */
    public List<Fluff> findByPerson(Person person, int first, int max) {
        Query q = query(where("personId").is(person.getId()));

        if (first > 0) {
            q.skip(first);
        }

        if (max > 0) {
            q.limit(max);
        }

        q.sort().on("created", Order.DESCENDING);
        return mongoOperations.find(q, Fluff.class);
    }

}
