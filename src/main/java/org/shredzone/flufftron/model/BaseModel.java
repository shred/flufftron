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
package org.shredzone.flufftron.model;

import java.io.Serializable;

import org.bson.types.ObjectId;

/**
 * Model entity superclass.
 *
 * @author Richard "Shred" Körber
 */
public abstract class BaseModel implements Serializable {
    private static final long serialVersionUID = -8779091300941595047L;

    private ObjectId id;

    public ObjectId getId()                     { return id; }
    public void setId(ObjectId id)              { this.id = id; }

}
