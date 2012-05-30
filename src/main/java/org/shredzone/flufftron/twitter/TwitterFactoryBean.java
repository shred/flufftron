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
package org.shredzone.flufftron.twitter;

import org.springframework.beans.factory.FactoryBean;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Helper class that enables twitter4j to be used with Spring.
 *
 * @author Richard "Shred" Körber
 */
public class TwitterFactoryBean implements FactoryBean<Twitter> {

    private final TwitterFactory factory;

    public TwitterFactoryBean(ConfigurationBuilder cb) {
        factory = new TwitterFactory(cb.build());
    }

    @Override
    public Twitter getObject() throws Exception {
        return factory.getInstance();
    }

    @Override
    public Class<?> getObjectType() {
        return Twitter.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

}
