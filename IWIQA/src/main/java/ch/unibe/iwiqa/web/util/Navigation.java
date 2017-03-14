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
    public static final String SINDEX = "s/sindex.xhtml";
    public static final String AINDEX = "a/aindex.xhtml";
    public static final String SFOKO = "sfoko.xhtml";
    public static final String AALLFOKOS = "aallfokos.xhtml";
    public static final String NEWQA = "newqa.xhtml";
    public static final String NEWFOKO ="anewfoko.xhtml";
    public static final String LOGIN = "login.xhtml";
    public static final String SLOGIN = "s/slogin.xhtml";
    public static final String ALOGIN = "a/alogin.xhtml";
    public static final String REGSUCCESS = "sregsuccess.xhtml";
    public static final String REGSTUDENT = "sreg.xhtml";
    public static final String REGADVISOR = "areg.xhtml";
    public static final String SQADETAIL = "sqadetail.xhtml";
    public static final String AQADETAIL = "aqadetail.xhtml";
    public static final String AFOKODETAIL = "afokodetail.xhtml";
    public static final String AALLQAS = "aallqas.xhtml";
    public static final String AMANADV = "amanadv.xhtml";

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
    
    public String getAALLFOKOS() {
        return AALLFOKOS;
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
    
    public String getSLOGIN(){
        return SLOGIN;
    }
    
    public String getALOGIN(){
        return ALOGIN;
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
    
    public String getAALLQAS() {
        return AALLQAS;
    }
    
    public String getAMANADV() {
        return AMANADV;
    }
    
    public String getAFOKODETAIL() {
        return AFOKODETAIL;
    }
}
