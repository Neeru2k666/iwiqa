/*
 */
package ch.unibe.iwiqa.web.bean;

import ch.unibe.iwiqa.entity.FoKo;
import ch.unibe.iwiqa.entity.dao.FoKoFacade;
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
@Named (value = "AAllFoKoBean")
@RequestScoped
public class AAllFoKoBean {
    
    
    private List<FoKo> allFoKos = new ArrayList<>();
    
    @Inject
    private FoKoFacade foKoFacade;
    
    @PostConstruct
    private void init(){
        allFoKos = foKoFacade.findAll();
    }

    public List<FoKo> getAllFoKos() {
        return allFoKos;
    }

    public void setAllFoKos(List<FoKo> allFoKos) {
        this.allFoKos = allFoKos;
    }
}
