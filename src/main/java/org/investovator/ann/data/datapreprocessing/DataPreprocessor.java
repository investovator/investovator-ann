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

    private double marketData[][];
    private ArrayList<TradingDataAttribute> attributes;
    private TradingDataAttribute attribute;
    private double[][] processedData;

    public double[][] preProcessData(double[][] marketData, ArrayList<TradingDataAttribute> attributes,
                                     TradingDataAttribute attribute){

        this.marketData = marketData;
        this.attributes = attributes;
        this.attribute = attribute;

        resolveNullValues();

        prepareData();

        return processedData;
    }

    private void resolveNullValues(){

        int rowCount = marketData.length;
        int colCount = marketData[0].length;

        for(int i = 0; i < rowCount; i++)
        {

            for(int j = 0; j < colCount; j++){

                double temp = marketData[i][j];
                if(temp == 0.0){

                    if(marketData[i + 1][j] == 0.0)
                        marketData[i][j] = marketData[i - 1][j];
                    else
                        marketData[i][j] = (marketData[i - 1][j] + marketData[i + 1][j]) / 2;

                }
            }

        }

    }

    private void prepareData(){

        int rowCount = marketData.length;
        int colCount = marketData[0].length;
        int attributeIndex = attributes.indexOf(attribute);

        processedData = new double[rowCount - 1][colCount + 1];

        for(int i = 0; i < rowCount - 1; i++)
        {

            for(int j = 0; j < colCount + 1; j++){

                if(j == colCount)
                    processedData[i][j] = marketData[i+1][attributeIndex];
                else
                    processedData[i][j] = marketData[i][j];

            }

        }

    }

}
