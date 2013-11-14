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

import org.investovator.ann.nngaming.events.Event;

import java.util.Observable;

/**
 * @author: Hasala Surasinghe
 * @version: ${Revision}
 */
public class MarketEventReceiver extends Observable {

    private static MarketEventReceiver instance;
    private Event event;

    public MarketEventReceiver() {
        event = new Event();
    }

    public static MarketEventReceiver getInstance() {
        if(instance == null){
            synchronized(EventScheduler.class){
                if(instance == null)
                    instance = new MarketEventReceiver();
            }
        }
        return instance;
    }

    public void setValue(Event newEvent) {
        // if value has changed notify observers
        if(!event.equals(newEvent)) {
            //System.out.println("Value changed to new value: "+newEvent);
            event = newEvent;

            // mark as value changed
            setChanged();
            // trigger notification
            notifyObservers(newEvent);
        }
    }
}
