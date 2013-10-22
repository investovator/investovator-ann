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

package org.investovator.ann.data.util;

/**
 * @author: Hasala Surasinghe
 * @author: Amila Surendra
 * @version: ${Revision}
 */
public enum InputTypes {
    DATE,
    STARTING_PRICE,
    CLOSING_PRICE,
    LOW_PRICE,
    HIGH_PRICE,
    SHARES_TRADED,
    TURNOVER,
    NO_OF_TRADES;

    public static String getInputTypes(InputTypes inputType) {

        switch (inputType) {
            case STARTING_PRICE:
                return "opening price";
            case CLOSING_PRICE:
                return "Closing";
            case LOW_PRICE:
                return "Low";
            case HIGH_PRICE:
                return "High";
            case SHARES_TRADED:
                return "No. of Shares";
            case TURNOVER:
                return "Turnover";
            case NO_OF_TRADES:
                return "No. of Trades";
            case DATE:
                return "Day";
        }
        return null;
    }
}
