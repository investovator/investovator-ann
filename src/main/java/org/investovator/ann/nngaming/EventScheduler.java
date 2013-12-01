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

    public final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    private final int DAY_LENGTH = 160;
    private final int BID_ADD_PERIOD = 46;
    private int speedFactor;
    private static EventScheduler instance;
    private MarketEventReceiver marketEventReceiver;
    private int dayLength;
    private int bidLength;

    private EventScheduler(){

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

        calculateSchedulingParams();

        service.scheduleAtFixedRate(dayChange,dayLength,dayLength, TimeUnit.SECONDS);
        service.scheduleAtFixedRate(bidAdd,0,bidLength,TimeUnit.SECONDS);

    }

    public void stopService() {
        service.shutdown();
    }

    private void calculateSchedulingParams(){

        dayLength = (DAY_LENGTH/speedFactor);
        bidLength = (BID_ADD_PERIOD/speedFactor);

    }

    public void setSpeedFactor(int speedFactor) {
        this.speedFactor = speedFactor;
    }
}