/*
 * investovator, Stock Market Gaming Framework
 *     Copyright (C) 2013  investovator
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.investovator.ann.nngaming;

import org.investovator.ann.nngaming.eventmanager.EventScheduler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author: Hasala Surasinghe
 * @version: ${Revision}
 */
public class EventSchedulerTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testStopService() throws Exception {
        EventScheduler eventScheduler = EventScheduler.getInstance();
        eventScheduler.stopService();
        assert (eventScheduler.service.isShutdown() == true);
    }
}
