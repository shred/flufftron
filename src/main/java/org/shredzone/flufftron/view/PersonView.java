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

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;
import org.shredzone.commons.view.annotation.PathPart;
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
 * Views for showing persons.
 *
 * @author Richard "Shred" Körber
 */
@ViewHandler
@Component
public class PersonView {
    private static final int MAX_PERSON_FLUFFS = 10;

    private @Resource PersonDao personDao;
    private @Resource FluffDao fluffDao;
    private @Resource FluffService fluffService;

    /**
     * Lists all persons registered with flufftron.
     */
    @View(pattern = "/for/index.html")
    public String personListView(HttpServletRequest req)
    throws ViewException, TwitterException {
        List<Person> persons = personDao.findAll();

        req.setAttribute("persons", persons);
        req.setAttribute("pageName", "person");
        return "view/personList.jsp";
    }

    /**
     * Shows an individual person.
     */
    @View(pattern = "/for/${person.nick}.html", signature = {"person"})
    public String personView(
            @PathPart("person.nick") Person person,
            HttpServletRequest req)
    throws ViewException, TwitterException {
        List<Fluff> fluffs = fluffDao.findByPerson(person, 0, MAX_PERSON_FLUFFS);
        Map<ObjectId, Person> persons = fluffService.getPersonMap(fluffs);

        req.setAttribute("person", person);
        req.setAttribute("persons", persons);
        req.setAttribute("fluffs", fluffs);
        req.setAttribute("pageName", "person");
        return "view/person.jsp";
    }

}
