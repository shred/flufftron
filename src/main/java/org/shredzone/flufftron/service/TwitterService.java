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

import java.util.Date;
import java.util.Iterator;

import javax.annotation.Resource;

import org.shredzone.flufftron.model.Fluff;
import org.shredzone.flufftron.model.Person;
import org.shredzone.flufftron.model.Timeline;
import org.shredzone.flufftron.repository.FluffDao;
import org.shredzone.flufftron.repository.PersonDao;
import org.springframework.stereotype.Service;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 * Twitter related services.
 *
 * @author Richard "Shred" Körber
 */
@Service
public class TwitterService {
    private static final String HASHTAG = "#flausch";

    private @Resource Twitter twitter;
    private @Resource PersonDao personDao;
    private @Resource FluffDao fluffDao;

    /**
     * Polls new fluff tweets for a {@link Person}.
     *
     * @param person
     *            {@link Person} to find fluff tweets for
     * @throws TwitterException
     *             if the fluff tweets could not be retrieved
     */
    public void pollNewFluffs(Person person) throws TwitterException {
        Timeline timeline = person.getTimeline();

        String user = timeline.getTwitter();
        if (user == null || user.isEmpty()) {
            return;
        }

        Query q = new Query().resultType("recent").rpp(50);
        q.query(HASHTAG + " @" + user);

        if (timeline.getLastId() != 0) {
            q.setSinceId(timeline.getLastId());
        }

        Date lastFluff = timeline.getLastFluff();

        QueryResult r = twitter.search(q);

        // The iterator is a workaround because twitter4j seems to be built in a funny
        // way that allows Generics and JDK1.4, but breaks compatibility with Java 7.
        Iterator<Tweet> it = r.getTweets().iterator();
        while (it.hasNext()) {
            Tweet tweet = it.next();

            if (user.equalsIgnoreCase(tweet.getFromUser())) {
                // ignore eigenflausch
                continue;
            }

            Fluff fluff = new Fluff();
            fluff.setPersonId(person.getId());
            fluff.setTwitId(tweet.getId());
            fluff.setCreated(tweet.getCreatedAt());
            fluff.setFrom(tweet.getFromUser());
            fluff.setText(tweet.getText());

            fluffDao.save(fluff);

            if (   lastFluff == null
                || (fluff.getCreated() != null && fluff.getCreated().after(lastFluff))) {
                lastFluff = fluff.getCreated();
            }
        }

        timeline.setLastId(r.getMaxId());
        timeline.setLastFluff(lastFluff);
        personDao.save(person);
    }

}
