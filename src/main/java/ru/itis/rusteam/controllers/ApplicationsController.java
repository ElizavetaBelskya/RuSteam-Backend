package ru.itis.rusteam.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.rusteam.controllers.api.ApplicationsApi;
import ru.itis.rusteam.dto.application.ApplicationDto;
import ru.itis.rusteam.dto.application.ApplicationsPage;
import ru.itis.rusteam.dto.application.NewOrUpdateApplicationDto;
import ru.itis.rusteam.services.ApplicationsService;


@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ApplicationsController implements ApplicationsApi {

    private final ApplicationsService applicationsService;

    @Override
    public ResponseEntity<ApplicationsPage> getAllApplications(int page, Double price, Double rating, String isNew,String category) {
        return ResponseEntity
                .ok(applicationsService.getAllApplications(page, price, rating, isNew, category));
    }

    @Override
    public ResponseEntity<ApplicationsPage> getApplicationsByContentString(String content, int page) {
        return ResponseEntity.ok(applicationsService.getAllApplicationByContentString(page, content));
    }


    @Override
    public ResponseEntity<ApplicationDto> addApplication(NewOrUpdateApplicationDto newApplication) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(applicationsService.addApplication(newApplication));
    }

    @Override
    public ResponseEntity<ApplicationDto> getApplication(Long applicationId) {
        return ResponseEntity
                .ok(applicationsService.getApplicationById(applicationId));
    }

    @Override
    public ResponseEntity<ApplicationDto> updateApplication(Long applicationId, NewOrUpdateApplicationDto updatedApplication) {
        return ResponseEntity.accepted()
                .body(applicationsService.updateApplication(applicationId, updatedApplication));
    }

    @Override
    public ResponseEntity<?> deleteApplication(Long applicationId) {
        applicationsService.deleteApplication(applicationId);
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<ApplicationDto> publishApplication(Long applicationId) {
        return ResponseEntity.accepted()
                .body(applicationsService.publishApplication(applicationId));
    }
}
