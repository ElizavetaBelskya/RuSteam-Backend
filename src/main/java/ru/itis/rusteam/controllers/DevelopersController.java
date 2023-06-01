package ru.itis.rusteam.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.rusteam.controllers.api.DevelopersApi;
import ru.itis.rusteam.dto.account.developer.DeveloperDto;
import ru.itis.rusteam.dto.account.developer.NewOrUpdateDeveloperDto;
import ru.itis.rusteam.dto.application.ApplicationsPage;
import ru.itis.rusteam.services.ApplicationsService;
import ru.itis.rusteam.services.DevelopersService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class DevelopersController implements DevelopersApi {
    private final ApplicationsService applicationsService;

    private final DevelopersService developersService;

    @Override
    public ResponseEntity<ApplicationsPage> getAllApplicationsByDeveloper(Long developerId, int page) {
        return ResponseEntity.ok(applicationsService.getAllApplicationsByDeveloperId(developerId, page));
    }

    @Override
    public ResponseEntity<DeveloperDto> addDeveloper(NewOrUpdateDeveloperDto newDeveloper) {
        return ResponseEntity.ok(developersService.addDeveloper(newDeveloper));
    }

    @Override
    public ResponseEntity<DeveloperDto> getDeveloperByAccountId(Long accountId) {
        return ResponseEntity.ok(developersService.getDeveloperByAccountId(accountId));
    }

    @Override
    public ResponseEntity<DeveloperDto> getDeveloperById(Long id) {
        return ResponseEntity.ok(developersService.getDeveloperById(id));
    }
}
