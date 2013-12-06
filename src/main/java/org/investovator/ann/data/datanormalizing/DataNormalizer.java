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

import org.encog.util.arrayutil.NormalizationAction;
import org.encog.util.arrayutil.NormalizedField;
import org.investovator.ann.nngaming.util.GameTypes;
import org.investovator.core.data.api.utils.TradingDataAttribute;

import java.util.ArrayList;

/**
 * @author: Hasala Surasinghe
 * @author: Amila Surendra
 * @version: ${Revision}
 */
public class DataNormalizer {

    private double min = 0;
    private double max = 0;
    private String symbol;
    private GameTypes gameType;

    public DataNormalizer(String symbol,GameTypes gameType){

        this.symbol = symbol;
        this.gameType = gameType;

    }


    public double[][] getNormalizedData(double [][] dataArray,ArrayList<TradingDataAttribute> attributes,String analysisSymbol){

        NormalizationModelSerializer serializer = new NormalizationModelSerializer();
        int columnCount = dataArray[0].length;
        int rowCount = dataArray.length;

        double [][] normalizedData = new double[rowCount][columnCount];

        /* min-max */
        for (int j = 0; j < columnCount; j++) {

            NormalizationModel model = new NormalizationModel();
            max = 0;
            for (int i = 0; i < rowCount; i++) {

                double tmp = dataArray[i][j];
                if (max < tmp) max=tmp;

            }

            min = max;

            for (int i = 0; i < rowCount; i++) {

                double tmp = dataArray[i][j];
                if (min > tmp) min=tmp;

            }

            model.setOldMax(max);
            model.setOldMin(min);

            if(j < (columnCount - 1) && (gameType == GameTypes.TRADING_GAME))
                serializer.saveModel(model, String.valueOf(attributes.get(j)), symbol, gameType);

            else if(j < (columnCount - 2) && (gameType == GameTypes.ANALYSIS_GAME))
                serializer.saveModel(model, String.valueOf(attributes.get(j)), symbol, gameType);

            else if(j == (columnCount - 2) && (gameType == GameTypes.ANALYSIS_GAME))
                serializer.saveModel(model, String.valueOf(attributes.get(j)),analysisSymbol,gameType);

            for (int i = 0; i < rowCount; i++) {

                normalizedData[i][j] = getNormalizedValue(dataArray[i][j]);

            }

        }

        return normalizedData;
    }

    private double getNormalizedValue(double value){

        NormalizedField norm = new NormalizedField(NormalizationAction.Normalize,null,max,min,1,0);

        return norm.normalize(value);
    }

    public double getNormalizedValue(double value, TradingDataAttribute tradingDataAttribute){

        NormalizationModel model = loadNormalizationModel(tradingDataAttribute);

        NormalizedField norm = new NormalizedField(NormalizationAction.Normalize,null,
                model.getOldMax(),model.getOldMin(),1,0);

        return norm.normalize(value);
    }

    public double getDenormalizedValue(double normalizedValue, TradingDataAttribute tradingDataAttribute){

        NormalizationModel model = loadNormalizationModel(tradingDataAttribute);

        NormalizedField norm = new NormalizedField(NormalizationAction.Normalize,null,
                model.getOldMax(),model.getOldMin(),1,0);

        return norm.deNormalize(normalizedValue);
    }


    private NormalizationModel loadNormalizationModel(TradingDataAttribute tradingDataAttribute){

        NormalizationModel model = null;
        NormalizationModelSerializer serializer = new NormalizationModelSerializer();

        if(tradingDataAttribute == TradingDataAttribute.CLOSING_PRICE)
        {
            model = serializer.readModel(String.valueOf(TradingDataAttribute.CLOSING_PRICE),symbol,gameType);
        }
        else if (tradingDataAttribute == TradingDataAttribute.HIGH_PRICE){
            model = serializer.readModel(String.valueOf(TradingDataAttribute.HIGH_PRICE),symbol,gameType);
        }
        else if (tradingDataAttribute == TradingDataAttribute.LOW_PRICE){
            model = serializer.readModel(String.valueOf(TradingDataAttribute.LOW_PRICE),symbol,gameType);
        }
        else if(tradingDataAttribute == TradingDataAttribute.TRADES){
            model = serializer.readModel(String.valueOf(TradingDataAttribute.TRADES),symbol,gameType);
        }
        else if(tradingDataAttribute == TradingDataAttribute.SHARES){
            model = serializer.readModel(String.valueOf(TradingDataAttribute.SHARES),symbol,gameType);
        }
        else if (tradingDataAttribute == TradingDataAttribute.TURNOVER){
            model = serializer.readModel(String.valueOf(TradingDataAttribute.TURNOVER),symbol,gameType);
        }

        return model;
    }

}
