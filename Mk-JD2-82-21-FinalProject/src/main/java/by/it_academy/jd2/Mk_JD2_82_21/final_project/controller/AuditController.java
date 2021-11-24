package by.it_academy.jd2.Mk_JD2_82_21.final_project.controller;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IAuditService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Audit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


// доделать !!!!
    @RestController
    @RequestMapping("/api/audit")
    public class AuditController {
        private final IAuditService iAuditService;

        public AuditController(IAuditService iAuditService) {
            this.iAuditService = iAuditService;
        }

        @RequestMapping(method = RequestMethod.GET, value = "/{id}")
        public ResponseEntity<?> get(@PathVariable("id") Long id){
            Audit audit = iAuditService.getAudit(id);
            return new ResponseEntity<>(audit, HttpStatus.OK);
        }

        @RequestMapping(method = RequestMethod.GET)
        public ResponseEntity<?> getAll(){
            List<Audit> audit = iAuditService.getAuditList();
            return new ResponseEntity<>(audit,HttpStatus.OK);
        }

    }
