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
    public static final String INDEX = "index.xhtml";
    public static final String SINDEX = "/s/sindex.xhtml";
    public static final String AINDEX = "/a/aindex.xhtml";
    public static final String SFOKO = "/s/sfoko.xhtml";
    public static final String AFOKO = "/a/afoko.xhtml";
    public static final String NEWQA = "newqa.xhtml";
    public static final String NEWFOKO ="/a/newfoko.xhtml";
    public static final String LOGIN = "login.xhtml";
    public static final String SLOGIN = "/s/slogin.xhtml";
    public static final String ALOGIN = "/a/alogin.xhtml";
    public static final String REGSUCCESS = "registerSuccess.xhtml";
    public static final String REGSTUDENT = "registerStudent.xhtml";
    public static final String REGADVISOR = "registerAdvisor.xhtml";
    public static final String SQADETAIL = "/s/sqadetail.xhtml";
    public static final String AQADETAIL = "/a/aqadetail.xhtml";

    public String getINDEX() {
        return INDEX;
    }
    
    public String getSINDEX() {
        return SINDEX;
    }

    public String getAINDEX() {
        return AINDEX;
    }
    
    public String getSFOKO() {
        return SFOKO;
    }
    
    public String getAFOKO() {
        return AFOKO;
    }

    public String getNEWQA() {
        return NEWQA;
    }
    
    public String getNEWFOKO() {
        return NEWFOKO;
    }
    
    public String getLOGIN(){
        return LOGIN;
    }
    
    public String getREGSUCCESS(){
        return REGSUCCESS;
    }
    
    public String getREGSTUDENT(){
        return REGSTUDENT;
    }
    
    public String getREGADVISOR(){
        return REGADVISOR;
    }
    
    public String getSQADETAIL() {
        return SQADETAIL;
    }
    
    public String getAQADETAIL() {
        return AQADETAIL;
    }
}
