package by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Audit;

import java.util.List;

public interface IAuditService {
    public void addAudit(Audit audit);
    public Audit getAudit(long auditId);
    public List<Audit> getAuditList();
    void updateAudit(Audit audit, long id);
    void deleteAudit(long id);
}
