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
import org.investovator.ann.nngaming.events.DayChangedEvent;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: Hasala Surasinghe
 * @version: ${Revision}
 */
public class EventScheduler extends Thread {

    private final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    private final int DAY_LENGTH = 10;
    private final int BID_ADD_PERIOD = 2;
    private static EventScheduler instance;
    private MarketEventReceiver marketEventReceiver;

    public EventScheduler(){

        marketEventReceiver = MarketEventReceiver.getInstance();

    }

    //Taking an instance of class contains your repeated method.
    TimerTask dayChange = new TimerTask() {
        @Override
        public void run() {
            marketEventReceiver.setValue(new DayChangedEvent());
        }
    };
    TimerTask bidAdd = new TimerTask() {
        @Override
        public void run() {
            marketEventReceiver.setValue(new AddBidEvent());
        }
    };

    public static EventScheduler getInstance() {
        if(instance == null){
            synchronized(EventScheduler.class){
                if(instance == null)
                    instance = new EventScheduler();
            }
        }
        return instance;
    }

    @Override
    public void run() {

        service.scheduleWithFixedDelay(dayChange,0,DAY_LENGTH, TimeUnit.SECONDS);
        service.scheduleWithFixedDelay(bidAdd,0,BID_ADD_PERIOD,TimeUnit.SECONDS);

    }

    public void stopService() {
        service.shutdown();
    }

}
