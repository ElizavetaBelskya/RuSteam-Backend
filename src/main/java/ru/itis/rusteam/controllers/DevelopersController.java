package ru.itis.rusteam.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.rusteam.controllers.api.DevelopersApi;
import ru.itis.rusteam.dto.application.ApplicationsPage;
import ru.itis.rusteam.services.ApplicationsService;

@RestController
@RequiredArgsConstructor
public class DevelopersController implements DevelopersApi {
    private final ApplicationsService applicationsService;

    @Override
    public ResponseEntity<ApplicationsPage> getAllApplicationsByDeveloper(Long developerId, int page) {
        return ResponseEntity.ok(applicationsService.getAllApplicationsByDeveloperId(developerId, page));
    }
}
