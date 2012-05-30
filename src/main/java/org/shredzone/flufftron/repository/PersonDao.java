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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.shredzone.flufftron.model.Person;
import org.shredzone.flufftron.model.Timeline;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository of {@link Person} entries.
 *
 * @author Richard "Shred" Körber
 */
@Repository
public class PersonDao implements BaseDao<Person> {

    private @Resource MongoOperations mongoOperations;

    @Override
    public Person findById(ObjectId id) {
        return mongoOperations.findById(id, Person.class);
    }

    @Override
    public void save(Person entry) {
        mongoOperations.save(entry);
    }

    @Override
    public void remove(Person entry) {
        mongoOperations.remove(entry);
    }

    /**
     * Returns all {@link Person}.
     *
     * @return List of all {@link Person}
     */
    public List<Person> findAll() {
        Query q = new Query();
        q.sort().on("name", Order.ASCENDING);
        return mongoOperations.find(q, Person.class);
    }

    /**
     * Finds a person by its nick.
     *
     * @param nick
     *            Nick of the person
     * @return {@link Person}, or {@code null} if there is no person with this nick
     */
    public Person findByNick(String nick) {
        return mongoOperations.findOne(query(where("nick").is(nick)), Person.class);
    }

    /**
     * Finds all {@link Person} with their {@link Timeline} due for a refetch.
     *
     * @return List of {@link Person}
     */
    public List<Person> findDueTimelime() {
        Date now = new Date();
        return mongoOperations.find(query(new Criteria().orOperator(
                   where("timeline.dueDate").lte(now),
                   where("timeline.dueDate").exists(false)
               )), Person.class);
    }

    /**
     * Finds all {@link Person} who have not or not recently received a fluff. Returns all
     * unfluffed persons, followed by all least recently fluffed persons.
     * <p>
     * If there are more than {@code max} unfluffed persons, the unfluffed persons are
     * returned in no certain order.
     *
     * @param max
     *            Maximum number of returned persons.
     * @return List of {@link Person}
     */
    public List<Person> findUnfluffed(int max) {
        Query q;
        List<Person> result = new ArrayList<Person>(max);

        // First find all unfluffed persons
        q = new Query(where("timeline.lastFluff").exists(false)).limit(max);
        result.addAll(mongoOperations.find(q, Person.class));

        // Now fill up with last recently fluffed persons
        if (result.size() < max) {
            q = new Query(where("timeline.lastFluff").exists(true)).limit(max - result.size());
            q.sort().on("timeline.lastFluff", Order.ASCENDING);
            result.addAll(mongoOperations.find(q, Person.class));
        }

        return result;
    }

}
