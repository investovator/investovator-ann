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

import org.investovator.ann.config.ConfigReceiver;
import org.investovator.ann.nngaming.util.GameTypes;
import org.investovator.core.data.api.utils.TradingDataAttribute;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author: Hasala Surasinghe
 * @version: ${Revision}
 */
public class NNPredictorTest {

    @Before
    public void setUp() throws Exception {

        ConfigReceiver configReceiver = new ConfigReceiver();
        configReceiver.setBasePath("src/test");

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetPredictedValue() throws Exception {
        NNPredictor nnPredictor = new NNPredictor();
        double[] inputData = {0.13533834586466165,0.1553627760252366,0.14520443255636226,3.372811770227051E-4,
                0.0158311345646438,2.2602577701101624E-4};

        double value = nnPredictor.getPredictedValue("RCL", TradingDataAttribute.CLOSING_PRICE,inputData,
                GameTypes.TRADING_GAME);
        assert (value == 104.13014494684876);

        value = nnPredictor.getPredictedValue("RCL", TradingDataAttribute.LOW_PRICE,inputData,
                GameTypes.TRADING_GAME);
        assert (value == 101.93043649386401);
    }
}
