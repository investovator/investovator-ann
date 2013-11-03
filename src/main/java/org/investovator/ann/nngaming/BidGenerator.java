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

import java.util.Random;

/**
 * @author: Hasala Surasinghe
 * @version: ${Revision}
 */
public class BidGenerator {

    private Random random = new Random();
    private double sampleSpread = 2;
    private int range = 2;

    public void generateSellOrders(double closingPrice){

        for (int i = 0; i < 5; i++) {
            System.out.println(closingPrice-sampleSpread/2 - random.nextFloat()*range);
        }

    }

    public void generateBuyOrders(double closingPrice){


        for (int i = 0; i < 5; i++) {
            System.out.println(closingPrice+sampleSpread/2 + random.nextFloat()*range);
        }

    }
}
