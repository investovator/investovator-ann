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

package org.investovator.ann.data.datapreprocessing;

import org.investovator.core.data.api.utils.TradingDataAttribute;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author: Hasala Surasinghe
 * @version: ${Revision}
 */
public class DataPreprocessorTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testPreProcessData() throws Exception {
        DataPreprocessor preprocessor = new DataPreprocessor();
        double [][] marketData = {{234.2,245.2,25.4,8,1000,12000},{234.2,245.2,25.4,8,1000,12000},
                {234.2,245.2,25.4,8,1000,12000}};

        ArrayList<TradingDataAttribute> attributes = new ArrayList<>();
        attributes.add(TradingDataAttribute.HIGH_PRICE);
        attributes.add(TradingDataAttribute.LOW_PRICE);
        attributes.add(TradingDataAttribute.CLOSING_PRICE);
        attributes.add(TradingDataAttribute.SHARES);
        attributes.add(TradingDataAttribute.TRADES);
        attributes.add(TradingDataAttribute.TURNOVER);

        double processedData[][] = preprocessor.preProcessData(marketData,attributes,TradingDataAttribute.CLOSING_PRICE);
        assert (processedData[0].length == marketData[0].length + 1);

    }
}
