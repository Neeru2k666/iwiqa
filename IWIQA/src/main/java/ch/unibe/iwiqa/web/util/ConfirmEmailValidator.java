package ch.unibe.iwiqa.web.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import javax.faces.validator.Validator;

/**
 * Validator for confirming password through the registration process.
 * 
 * @author Marc Jost
 * @version 1.0
 */
@FacesValidator("confirmEmailValidator")
public class ConfirmEmailValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String email = (String) value;
        String confirm = (String) component.getAttributes().get("confirmEmail");

        if (email == null || confirm == null) {
            return; // Just ignore and let required="true" do its job.
        }

        if (!email.equals(confirm)) {
            throw new ValidatorException(new FacesMessage("Emails sind nicht identisch."));
        }
    }
}