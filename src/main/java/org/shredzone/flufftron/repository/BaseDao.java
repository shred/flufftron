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

import org.bson.types.ObjectId;
import org.shredzone.flufftron.model.BaseModel;

/**
 * Base repository interface.
 *
 * @author Richard "Shred" Körber
 */
public interface BaseDao<T extends BaseModel> {

    /**
     * Finds an object by its ID.
     *
     * @param id
     *            ID of the object
     * @return object found, or {@code null} if there is no such object
     */
    T findById(ObjectId id);

    /**
     * Saves the current state of the object. If the object was not saved before, it is
     * inserted into the database and an ID is generated.
     *
     * @param entry
     *            entry to be saved
     */
    void save(T entry);

    /**
     * Removes an entry from the database.
     *
     * @param entry
     *            entry to be removed
     */
    void remove(T entry);

}
