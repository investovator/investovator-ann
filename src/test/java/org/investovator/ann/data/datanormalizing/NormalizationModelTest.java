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

import org.junit.Test;

/**
 * @author: Hasala Surasinghe
 * @version: ${Revision}
 */
public class NormalizationModelTest {
    @Test
    public void testGetOldMax() throws Exception {
        NormalizationModel normalizationModel = new NormalizationModel();
        normalizationModel.setOldMax(24.5);
        assert (normalizationModel.getOldMax() == 24.5);
    }

    @Test
    public void testSetOldMax() throws Exception {
        NormalizationModel normalizationModel = new NormalizationModel();
        normalizationModel.setOldMax(24.5);
        assert (normalizationModel.getOldMax() == 24.5);
    }

    @Test
    public void testGetNewMax() throws Exception {
        NormalizationModel normalizationModel = new NormalizationModel();
        normalizationModel.setNewMax(24.5);
        assert (normalizationModel.getNewMax() == 24.5);
    }

    @Test
    public void testSetNewMax() throws Exception {
        NormalizationModel normalizationModel = new NormalizationModel();
        normalizationModel.setNewMax(24.5);
        assert (normalizationModel.getNewMax() == 24.5);
    }

    @Test
    public void testGetOldMin() throws Exception {
        NormalizationModel normalizationModel = new NormalizationModel();
        normalizationModel.setOldMin(14.2);
        assert (normalizationModel.getOldMin() == 14.2);
    }

    @Test
    public void testSetOldMin() throws Exception {
        NormalizationModel normalizationModel = new NormalizationModel();
        normalizationModel.setOldMin(14.2);
        assert (normalizationModel.getOldMin() == 14.2);
    }

    @Test
    public void testGetNewMin() throws Exception {
        NormalizationModel normalizationModel = new NormalizationModel();
        normalizationModel.setNewMin(14.2);
        assert (normalizationModel.getNewMin() == 14.2);
    }

    @Test
    public void testSetNewMin() throws Exception {
        NormalizationModel normalizationModel = new NormalizationModel();
        normalizationModel.setNewMin(14.2);
        assert (normalizationModel.getNewMin() == 14.2);
    }
}
