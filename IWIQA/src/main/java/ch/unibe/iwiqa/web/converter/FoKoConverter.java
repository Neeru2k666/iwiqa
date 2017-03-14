/*
 */
package ch.unibe.iwiqa.web.converter;

import ch.unibe.iwiqa.entity.FoKo;
import ch.unibe.iwiqa.entity.dao.FoKoFacade;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Marc Jost
 */
@Named(value = "fokoconverter")
@ApplicationScoped
public class FoKoConverter implements Converter{
    
    @Inject
    private FoKoFacade fokoFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            Class<?> type = component.getValueExpression("value").getType(context.getELContext());
            return fokoFacade.find(Long.valueOf(value));
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid ID of FoKo", value)), e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }

        if (value instanceof FoKo) {
            Number id = ((FoKo) value).getId();
            return (id != null) ? id.toString() : null;
        } else {
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid FoKo", value)));
        }
    }
}
