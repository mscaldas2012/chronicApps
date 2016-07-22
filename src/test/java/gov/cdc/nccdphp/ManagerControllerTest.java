package gov.cdc.nccdphp;

import gov.cdc.nccdphp.controllers.ManagerController;
import gov.cdc.nccdphp.domain.Manager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import static org.junit.Assert.*;

/**
 * Created by marcelo on 7/18/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChronicAppsApplication.class)
@WebAppConfiguration
public class ManagerControllerTest {

    @Autowired
    private ManagerController mgrCtrlr;
    @Test
    public void testInvalidEmail() throws Exception {
        Manager manager = new Manager();
        manager.setName("Test");
        manager.setEmail("invvalid.email.com");
        manager.setPhoneNumber("555-123-4567");
        BindingResult bindingResult = new BeanPropertyBindingResult(mgrCtrlr, "command");
        try {
            mgrCtrlr.save(manager, bindingResult);
        } catch (Exception e) {
            System.out.println("e = " + e);
            assertTrue(true);
        }
    }
    @Test
    public void testInvalidPhoneNumber() throws Exception {
        Manager manager = new Manager();
        manager.setName("Test");
        manager.setEmail("valid@email.com");
        manager.setPhoneNumber("+1(404)679934");
        BindingResult bindingResult = new BeanPropertyBindingResult(mgrCtrlr, "command");
        try {
            mgrCtrlr.save(manager, bindingResult);
            assertTrue(false);
        } catch (Exception e) {
            System.out.println("e = " + e);
            assertTrue(true);
        }

    }
}