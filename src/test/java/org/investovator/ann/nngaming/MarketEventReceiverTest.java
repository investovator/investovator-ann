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

import org.investovator.ann.nngaming.events.AddBidEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Observable;
import java.util.Observer;

import static junit.framework.Assert.assertTrue;

/**
 * @author: Hasala Surasinghe
 * @version: ${Revision}
 */
public class MarketEventReceiverTest {

    @Before
    public void setUp() throws Exception {
        class Listener implements Observer{

            @Override
            public void update(Observable o, Object arg) {
                assertTrue(arg instanceof AddBidEvent);
            }
        }

        Listener listener = new Listener();

        MarketEventReceiver.getInstance().addObserver(listener);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSetValue() throws Exception {
        MarketEventReceiver marketEventReceiver = MarketEventReceiver.getInstance();
        marketEventReceiver.setValue(new AddBidEvent());

    }
}
