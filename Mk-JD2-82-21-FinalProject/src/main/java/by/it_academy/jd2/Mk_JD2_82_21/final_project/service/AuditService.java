package by.it_academy.jd2.Mk_JD2_82_21.final_project.service;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IAuditService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.dao.IAuditDAO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Audit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditService implements IAuditService {
    private static IAuditDAO auditDAO;

    public AuditService(IAuditDAO auditDAO) {
        this.auditDAO = auditDAO;
    }
    @Override
    public void addAudit(Audit audit) {
        auditDAO.save(audit);
    }

    @Override
    public Audit getAudit(long auditId) {
        return auditDAO.findById(auditId).orElseThrow(()-> new IllegalArgumentException ("По данному ID аудит не найден"));
    }

    @Override
    public List<Audit> getAuditList() {
        return auditDAO.findAll();
    }

    @Override
    public void updateAudit(Audit audit, long id) {
        Audit updateAudit = getAudit(id);
        updateAudit.setUser(audit.getUser());
        updateAudit.setActionInformation(audit.getActionInformation());
        updateAudit.setEntityType(audit.getEntityType());
        updateAudit.setIdEntityOnWithTheActionIsPerformed(audit.getIdEntityOnWithTheActionIsPerformed());
        auditDAO.save(updateAudit);
    }

    @Override
    public void deleteAudit(long id) {
        auditDAO.deleteById(id);
    }
}
