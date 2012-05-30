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
package org.shredzone.flufftron.view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;
import org.shredzone.commons.view.annotation.View;
import org.shredzone.commons.view.annotation.ViewHandler;
import org.shredzone.commons.view.exception.ViewException;
import org.shredzone.flufftron.model.Fluff;
import org.shredzone.flufftron.model.Person;
import org.shredzone.flufftron.repository.FluffDao;
import org.shredzone.flufftron.repository.PersonDao;
import org.shredzone.flufftron.service.FluffService;
import org.springframework.stereotype.Component;

import twitter4j.TwitterException;

/**
 * Views for showing fluffs.
 *
 * @author Richard "Shred" Körber
 */
@ViewHandler
@Component
public class FluffView {
    private static final int MAX_FLUFFS = 20;
    private static final int MAX_UNFLUFFS = 5;

    private @Resource PersonDao personDao;
    private @Resource FluffDao fluffDao;
    private @Resource FluffService fluffService;

    /**
     * Lists all the latest fluffs.
     */
    @View(pattern = "/index.html")
    public String helloWorldView(HttpServletRequest req)
    throws ViewException, TwitterException {
        List<Fluff> fluffs = fluffDao.findAll(0, MAX_FLUFFS);
        Map<ObjectId, Person> persons = fluffService.getPersonMap(fluffs);

        req.setAttribute("fluffs", fluffs);
        req.setAttribute("persons", persons);
        req.setAttribute("pageName", "index");
        return "view/index.jsp";
    }

    /**
     * Lists all unfluffed persons.
     */
    @View(pattern = "/unfluffed.html")
    public String unfluffedView(HttpServletRequest req)
    throws ViewException, TwitterException {
        long current = System.currentTimeMillis();
        Map<Person, Interval> timeMap = new HashMap<Person, Interval>();

        List<Person> unfluffed = personDao.findUnfluffed(MAX_UNFLUFFS);
        for (Person p : unfluffed) {
            if (p.getTimeline().getLastFluff() != null) {
                long age = current - p.getTimeline().getLastFluff().getTime();
                timeMap.put(p, new Interval(age));
            } else {
                timeMap.put(p, null);
            }
        }

        req.setAttribute("unfluffed", unfluffed);
        req.setAttribute("timeMap", timeMap);
        req.setAttribute("pageName", "unfluffed");
        return "view/unfluffed.jsp";
    }

    /**
     * DTO for time intervals. Returns days, hours and minutes for the amount of
     * milliseconds passed in. Objects are immutable.
     */
    public static class Interval implements Serializable {
        private static final long serialVersionUID = -5036813452618691664L;

        private final int days;
        private final int hours;
        private final int minutes;

        public Interval(long ms) {
            days = (int) (ms / (24 * 60 * 60 * 1000L));
            hours = (int) (ms / (60 * 60 * 1000L)) % 24;
            minutes = (int) (ms / (60 * 1000L)) % 60;
        }

        public int getDays()                    { return days; }
        public int getHours()                   { return hours; }
        public int getMinutes()                 { return minutes; }
    }

}
