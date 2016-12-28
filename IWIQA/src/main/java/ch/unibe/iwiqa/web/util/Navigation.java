/*
 */
package ch.unibe.iwiqa.web.util;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author Marc Jost
 */
@Named
@ApplicationScoped
public class Navigation {
    public static final String SINDEX = "sindex.xhtml";
    public static final String BINDEX = "bindex.xthml";
    public static final String FOKO = "foko.xhtml";
    public static final String NEWQA = "newqa.xhtml";

    public String getSINDEX() {
        return SINDEX;
    }

    public String getBINDEX() {
        return BINDEX;
    }
    
    public String getFOKO() {
        return FOKO;
    }

    public String getNEWQA() {
        return NEWQA;
    }
    
}
