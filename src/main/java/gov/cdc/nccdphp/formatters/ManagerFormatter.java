package gov.cdc.nccdphp.formatters;

import gov.cdc.nccdphp.domain.Manager;
import gov.cdc.nccdphp.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Created by marcelo on 7/14/16.
 */
@Component
public class ManagerFormatter implements Formatter<Manager> {
    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public Manager parse(String text, Locale locale) throws ParseException {
        return managerRepository.findOne(Long.valueOf(text));
    }

    @Override
    public String print(Manager object, Locale locale) {

        return String.valueOf(object.getId());
    }
}
