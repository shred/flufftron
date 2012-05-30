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
import java.util.Date;

/**
 * A reference to the Twitter timeline of a person.
 *
 * @author Richard "Shred" Körber
 */
public class Timeline implements Serializable {
    private static final long serialVersionUID = 4104610943416078513L;

    private String twitter;
    private Date dueDate;
    private long lastId;
    private Date lastFluff;

    public String getTwitter()                  { return twitter; }
    public void setTwitter(String twitter)      { this.twitter = twitter; }

    public Date getDueDate()                    { return dueDate;}
    public void setDueDate(Date dueDate)        { this.dueDate = dueDate; }

    public long getLastId()                     { return lastId; }
    public void setLastId(long lastId)          { this.lastId = lastId; }

    public Date getLastFluff()                  { return lastFluff; }
    public void setLastFluff(Date lastFluff)    { this.lastFluff = lastFluff; }

}
