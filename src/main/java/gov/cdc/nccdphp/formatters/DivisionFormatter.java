package gov.cdc.nccdphp.formatters;

import gov.cdc.nccdphp.domain.Division;
import gov.cdc.nccdphp.repositories.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Created by marcelo on 7/14/16.
 *
 * This was very painful...
 * So, it turns out that in order to make a Drop Down $lt;select$gt; work for Entity objects (Foreign Keys)
 * You need this type of Class as a Formatter...
 *
 * For this project, for example, my class Project has a Division, which I wanted the user to choose it from
 * a drop down. In order to appropriately populate the division property on my Project class, this class does the trick
 * (by hitting the DB on the parse method and retrieving the actual object.)
 *
 * Need to see if can use some caching or something when performance is of issue.
 */
@Component
public class DivisionFormatter implements Formatter<Division> {

    @Autowired
    private DivisionRepository divisionRepository;

    @Override
    public Division parse(String text, Locale locale) throws ParseException {
        //When adding a "Seleect Division" Option on the drop down with a value of -1, the findOne was retrieving
        //Null value, which threw an error saying this method cannot return Null.
        //Therefore, a dummy Division was created!
        Division division = divisionRepository.findOne(Integer.valueOf(text));
        return division == null?new Division():division;
    }

    @Override
    public String print(Division object, Locale locale) {
        return String.valueOf(object.getId());
    }
}
