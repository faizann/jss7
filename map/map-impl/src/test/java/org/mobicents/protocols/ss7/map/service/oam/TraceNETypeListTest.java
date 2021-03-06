/*
 * TeleStax, Open Source Cloud Communications  Copyright 2012.
 * and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.mobicents.protocols.ss7.map.service.oam;

import static org.testng.Assert.*;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class TraceNETypeListTest {

    private byte[] getEncodedData() {
        return new byte[] { 3, 3, 6, (byte) 219, 64 };
    }

    @Test(groups = { "functional.decode", "service.oam" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        TraceNETypeListImpl asc = new TraceNETypeListImpl();
        asc.decodeAll(asn);

        assertEquals(tag, Tag.STRING_BIT);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertTrue(asc.getMscS());
        assertTrue(asc.getMgw());
        assertFalse(asc.getSgsn());
        assertTrue(asc.getGgsn());
        assertTrue(asc.getRnc());
        assertFalse(asc.getBmSc());
        assertTrue(asc.getMme());
        assertTrue(asc.getSgw());
        assertFalse(asc.getPgw());
        assertTrue(asc.getEnb());

    }

    @Test(groups = { "functional.encode", "service.oam" })
    public void testEncode() throws Exception {

        TraceNETypeListImpl asc = new TraceNETypeListImpl(true, true, false, true, true, false, true, true, false, true);
//        boolean mscS, boolean mgw, boolean sgsn, boolean ggsn, boolean rnc, 
//        boolean bmSc, boolean mme, boolean sgw, boolean pgw, boolean enb

        AsnOutputStream asnOS = new AsnOutputStream();
        asc.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));

    }

}
