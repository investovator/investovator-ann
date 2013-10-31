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

import java.util.ArrayList;

/**
 * @author: Hasala Surasinghe
 * @version: ${Revision}
 */
public class DataPreprocessor {



    public double[][] preProcessData(double[][] marketData, ArrayList<TradingDataAttribute> attributes,
                                     TradingDataAttribute attribute){

        int rowCount = marketData.length;
        int colCount = marketData[0].length;
        int attributeIndex = attributes.indexOf(attribute);

        double[][] processedData = new double[rowCount - 1][colCount + 1];

        for(int i = 0; i < rowCount - 1; i++)
        {

           for(int j = 0; j < colCount + 1; j++){

               if(j == colCount)
                processedData[i][j] = marketData[i+1][attributeIndex];
               else
                processedData[i][j] = marketData[i][j];

           }

        }
        return processedData;
    }

    private void resolveNullValues(){

    }

}
