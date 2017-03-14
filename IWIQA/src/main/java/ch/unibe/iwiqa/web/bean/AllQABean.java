/*
 */
package ch.unibe.iwiqa.web.bean;

import ch.unibe.iwiqa.entity.QA;
import ch.unibe.iwiqa.entity.dao.QAFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Marc Jost
 */
@Named
@RequestScoped
public class AllQABean implements Serializable {
    
    private static final long serialVersionUID = -3224025447348839378L;
    
    @Inject
    private QAFacade qAFacade;
    
    private List<QA> qas = new ArrayList<>();

    @PostConstruct
    private void init(){
        qas = qAFacade.findAll();
    }
    
    public List<QA> getQas() {
        return qas;
    }

    public void setQas(List<QA> qas) {
        this.qas = qas;
    }
}
