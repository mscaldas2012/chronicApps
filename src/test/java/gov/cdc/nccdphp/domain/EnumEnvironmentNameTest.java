package gov.cdc.nccdphp.domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by marcelo on 7/18/16.
 */
public class EnumEnvironmentNameTest {

    @Test
    public void testEquals() {
        Deployment d = new Deployment();
        d.setEnvironment(EnumEnvironmentName.OTHER);
        assertTrue(d.getEnvironment().equals(EnumEnvironmentName.OTHER));
        assertTrue(d.getEnvironment() == EnumEnvironmentName.OTHER);
    }

}