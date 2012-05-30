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

/**
 * A single fluffable person.
 *
 * @author Richard "Shred" Körber
 */
public class Person extends BaseModel {
    private static final long serialVersionUID = -7161862470373010243L;

    private String nick;
    private String name;
    private String solidName;
    private Timeline timeline;

    public String getNick()                     { return nick; }
    public void setNick(String nick)            { this.nick = nick; }

    public String getName()                     { return name; }
    public void setName(String name)            { this.name = name; }

    public String getSolidName()                { return solidName; }
    public void setSolidName(String solidName)  { this.solidName = solidName; }

    public Timeline getTimeline()               { return timeline; }
    public void setTimeline(Timeline timeline)  { this.timeline = timeline; }

}
