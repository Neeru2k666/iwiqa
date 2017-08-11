/*
 */
package ch.unibe.iwiqa.web.bean;

import ch.unibe.iwiqa.entity.Advisor;
import ch.unibe.iwiqa.entity.IWIQASettings;
import ch.unibe.iwiqa.entity.Professor;
import ch.unibe.iwiqa.entity.QA;
import ch.unibe.iwiqa.entity.Student;
import ch.unibe.iwiqa.entity.dao.IWIQASettingsFacade;
import ch.unibe.iwiqa.entity.dao.QAFacade;
import ch.unibe.iwiqa.util.QA_Status;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import java.util.HashMap;
import javax.inject.Inject;
import org.docx4j.model.datastorage.migration.VariablePrepare;

/**
 *
 * @author Marc Jost
 */
@Named
@RequestScoped
public class CreateGradeAnnouncementBean {

    @Inject
    private QAFacade qAFacade;
    
    @Inject
    private IWIQASettingsFacade iWIQASettingsFacade;

    public void create(QA qa) {
        try {

            Student s = qa.getStudent();
            Advisor a = qa.getAdvisor();
            Professor p = a.getProfessor();
            
            SimpleDateFormat sdfDoc = new SimpleDateFormat("dd. MMM. yyyy");
            SimpleDateFormat sdfFilename = new SimpleDateFormat("yyyyMMdd");
            org.docx4j.wml.ObjectFactory foo = Context.getWmlObjectFactory();
            
            IWIQASettings settings = iWIQASettingsFacade.findAll().get(0);
            if(settings == null) throw new Exception("IWIQASettings could not be loaded from DB.");
            
            // Input docx has variables in it: ${colour}, ${icecream}
            String inputfilepath = settings.getPathGradeAnnouncementTemplate();
            String outputFilename = sdfFilename.format(new Date()) + "_Notenmeldung_" + qa.getQaType().getAbbreviated() + "_" + s.getLastName() + "_" + s.getFirstName();
            String outputfilepath = settings.getPathGradeAnnouncementFolder() + "\\" + outputFilename + ".docx";

            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
                    .load(new java.io.File(inputfilepath));
            VariablePrepare.prepare(wordMLPackage);
            MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();

            HashMap<String, String> mappings = new HashMap<String, String>();
            mappings.put("qa_type1", qa.getQaType().getUnabbreviated());
            mappings.put("qa_type2", qa.getQaType().getUnabbreviated());
            mappings.put("student_fullname", s.getFullName());
            mappings.put("student_matnr", s.getMatNr());
            mappings.put("qa_title", qa.getTitle());
            mappings.put("qa_handindate", sdfDoc.format(qa.getHandInDate()));
            mappings.put("qa_grade", String.valueOf(qa.getGrade()));
            mappings.put("date_today", sdfDoc.format(new Date()));
            mappings.put("prof_title_and_name", p.getFullnameWithTitles());
            mappings.put("advisor_fullname", a.getFullName());
            mappings.put("advisor_phone", a.getEmail());
            mappings.put("student_address", s.getStreet() + ", " + s.getZIP() + " " + s.getCity());

            // Approach 1 (from 3.0.0; faster if you haven't yet caused unmarshalling to occur):
            documentPart.variableReplace(mappings);

            // Save it
            SaveToZipFile saver = new SaveToZipFile(wordMLPackage);
            saver.save(outputfilepath);

            qa.setGradeAnnouncementLocalURI(outputfilepath);
            qa.setStatus(QA_Status.QA_COMPLETED);
            qAFacade.edit(qa);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
