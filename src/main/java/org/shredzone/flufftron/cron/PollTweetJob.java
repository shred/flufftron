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
package org.shredzone.flufftron.cron;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.shredzone.flufftron.model.Person;
import org.shredzone.flufftron.model.Timeline;
import org.shredzone.flufftron.repository.PersonDao;
import org.shredzone.flufftron.service.TwitterService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import twitter4j.TwitterException;

/**
 * Periodically checks Twitter for fluff tweets.
 *
 * @author Richard "Shred" Körber
 */
@Component
public class PollTweetJob {
    private static final Log LOG = LogFactory.getLog(PollTweetJob.class);

    private static final long MINUTES = 60 * 1000L;

    private static final long CHECK_FREQUENCY = 5 * MINUTES;
    private static final long POLL_FREQUENCY = 60 * MINUTES;

    private @Resource PersonDao personDao;
    private @Resource TwitterService twitterService;

    /**
     * Polls all due timelines for fluff tweets.
     */
    @Scheduled(fixedRate = CHECK_FREQUENCY)
    public void pollDueTweets() {
        Date nextDueDate = new Date(System.currentTimeMillis() + POLL_FREQUENCY);

        for (Person person : personDao.findDueTimelime()) {
            Timeline timeline = person.getTimeline();

            // no matter what happens next, set the new due date to avoid endless loops
            timeline.setDueDate(nextDueDate);
            personDao.save(person);

            // now update the tweets
            try {
                twitterService.pollNewFluffs(person);
            } catch (TwitterException ex) {
                LOG.error("Could not poll fluff tweets for @" + timeline.getTwitter(), ex);
            }
        }
    }

}
