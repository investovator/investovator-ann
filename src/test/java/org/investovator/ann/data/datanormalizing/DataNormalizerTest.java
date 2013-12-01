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

package org.investovator.ann.data.datanormalizing;

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
public class DataNormalizerTest {

    @Before
    public void setUp() throws Exception {

        ConfigReceiver configReceiver = new ConfigReceiver();
        configReceiver.setBasePath("src/test");

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetNormalizedValue() throws Exception {

        DataNormalizer dataNormalizer = new DataNormalizer("SAMP", GameTypes.TRADING_GAME);
        assert (dataNormalizer.getNormalizedValue(200, TradingDataAttribute.HIGH_PRICE) == 0.12521869532616844);
        assert (dataNormalizer.getNormalizedValue(200,TradingDataAttribute.LOW_PRICE) == 0.13570487483530963);
        assert (dataNormalizer.getNormalizedValue(200,TradingDataAttribute.TRADES) == 0.2628797886393659);
        assert (dataNormalizer.getNormalizedValue(200,TradingDataAttribute.SHARES) == 1.2427978312183607E-5);
        assert (dataNormalizer.getNormalizedValue(20000,TradingDataAttribute.TURNOVER) == 1.1834216631964977E-7);

        DataNormalizer dataNormalizer1 = new DataNormalizer("RCL",GameTypes.TRADING_GAME);
        assert (dataNormalizer1.getNormalizedValue(200, TradingDataAttribute.HIGH_PRICE) == 0.48872180451127817);
        assert (dataNormalizer1.getNormalizedValue(200,TradingDataAttribute.CLOSING_PRICE) == 0.5043943446694689);
        assert (dataNormalizer1.getNormalizedValue(200,TradingDataAttribute.LOW_PRICE) == 0.5264195583596214);
        assert (dataNormalizer1.getNormalizedValue(200,TradingDataAttribute.TRADES) == 0.525065963060686);
        assert (dataNormalizer1.getNormalizedValue(200,TradingDataAttribute.SHARES) == 5.877316482269554E-5);
        assert (dataNormalizer1.getNormalizedValue(200,TradingDataAttribute.TURNOVER) == 1.979895261896959E-7);

    }

    @Test
    public void testGetDenormalizedValue() throws Exception {

        DataNormalizer dataNormalizer = new DataNormalizer("SAMP", GameTypes.TRADING_GAME);
        assert (dataNormalizer.getDenormalizedValue(0.12521869532616844, TradingDataAttribute.HIGH_PRICE) == 200);
        assert (dataNormalizer.getDenormalizedValue(0.1344998694176025, TradingDataAttribute.CLOSING_PRICE) == -0.0);
        assert (dataNormalizer.getDenormalizedValue(0.13570487483530963, TradingDataAttribute.LOW_PRICE) == 200);
        assert (dataNormalizer.getDenormalizedValue(0.2628797886393659, TradingDataAttribute.TRADES) == 199.99999999999997);
        assert (dataNormalizer.getDenormalizedValue(1.2427978312183607E-5, TradingDataAttribute.SHARES) == 200);
        assert (dataNormalizer.getDenormalizedValue(1.1834216631964977E-7, TradingDataAttribute.TURNOVER) == 20000);

        DataNormalizer dataNormalizer1 = new DataNormalizer("RCL",GameTypes.TRADING_GAME);
        assert (dataNormalizer1.getDenormalizedValue(0.48872180451127817, TradingDataAttribute.HIGH_PRICE) == 200);
        assert (dataNormalizer1.getDenormalizedValue(0.5264195583596214, TradingDataAttribute.LOW_PRICE) == 200);
        assert (dataNormalizer1.getDenormalizedValue(0.525065963060686, TradingDataAttribute.TRADES) == 200);
        assert (dataNormalizer1.getDenormalizedValue(5.877316482269554E-5, TradingDataAttribute.SHARES) == 200);
        assert (dataNormalizer1.getDenormalizedValue(1.979895261896959E-7, TradingDataAttribute.TURNOVER) == 200);

    }
}
