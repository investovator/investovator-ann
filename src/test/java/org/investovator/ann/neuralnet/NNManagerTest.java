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

package org.investovator.ann.neuralnet;

import org.junit.After;
import org.junit.Before;

/**
 * @author: Hasala Surasinghe
 * @version: ${Revision}
 */
public class NNManagerTest {
    @Before
    public void setUp() throws Exception {

        System.setProperty("org.investovator.core.data.cassandra.url", "localhost:9160" );
        System.setProperty("org.investovator.core.data.cassandra.username", "admin" );
        System.setProperty("org.investovator.core.data.cassandra.password", "admin" );

    }

    @After
    public void tearDown() throws Exception {

    }

//    @Test
//    public void testCreateGamingNeuralNetworks() throws Exception {
//        ArrayList<TradingDataAttribute> inputParameters = new ArrayList<>();
//        inputParameters.add(TradingDataAttribute.CLOSING_PRICE);
//        inputParameters.add(TradingDataAttribute.HIGH_PRICE);
//        inputParameters.add(TradingDataAttribute.LOW_PRICE);
//
//        ArrayList<String> stockIDs = new ArrayList<>();
//        stockIDs.add("SAMP");
//        stockIDs.add("RCL");
//        stockIDs.add("HASU");
//
//        ArrayList<String> analysisParams = new ArrayList<>();
//        analysisParams.add("SAMP Stock Price");
//        analysisParams.add("RCL Stock Price");
//        analysisParams.add("HASU Stock Price");
//
//        NNManager nnManager = new NNManager(inputParameters, stockIDs,analysisParams);
//        nnManager.createGamingNeuralNetworks();
//    }
}
