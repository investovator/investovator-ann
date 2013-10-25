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

package org.investovator.ann.data;

import org.investovator.core.data.api.CompanyStockTransactionsData;
import org.investovator.core.data.api.CompanyStockTransactionsDataImpl;
import org.investovator.core.data.api.utils.StockTradingData;
import org.investovator.core.data.api.utils.TradingDataAttribute;
import org.investovator.core.data.exeptions.DataAccessException;
import org.investovator.core.data.exeptions.DataNotFoundException;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author: Hasala Surasinghe
 * @version: ${Revision}
 */
public class DataRetriever {

    private final CompanyStockTransactionsData.DataType type;
    private CompanyStockTransactionsDataImpl companyStockTransactionsData;
    private StockTradingData stockTradingData;
    private String symbol;
    private ArrayList<TradingDataAttribute> attributes;
    private Date startingDate;
    private Date endDate;
    private int numOfRows;

    public DataRetriever(String symbol){

        this.companyStockTransactionsData = new CompanyStockTransactionsDataImpl();
        this.type = CompanyStockTransactionsData.DataType.OHLC;
        this.symbol = symbol;
    }

    public StockTradingData getTrainingData(){
        retrieveTrainingData();

        return stockTradingData;
    }

    private void retrieveTrainingData(){
        try {
            stockTradingData = companyStockTransactionsData.getTradingData(type,symbol,startingDate,endDate,
                    numOfRows,attributes);
        } catch (DataNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (DataAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
