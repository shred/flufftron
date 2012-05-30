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

import java.util.Date;

import org.bson.types.ObjectId;

/**
 * A single fluff tweet.
 *
 * @author Richard "Shred" Körber
 */
public class Fluff extends BaseModel {
    private static final long serialVersionUID = -6994228681971003672L;

    private ObjectId personId;
    private long twitId;
    private Date created;
    private String from;
    private String text;

    public ObjectId getPersonId()               { return personId; }
    public void setPersonId(ObjectId personId)  { this.personId = personId; }

    public long getTwitId()                     { return twitId; }
    public void setTwitId(long twitId)          { this.twitId = twitId; }

    public Date getCreated()                    { return created; }
    public void setCreated(Date created)        { this.created = created; }

    public String getFrom()                     { return from; }
    public void setFrom(String from)            { this.from = from; }

    public String getText()                     { return text; }
    public void setText(String text)            { this.text = text; }

}
