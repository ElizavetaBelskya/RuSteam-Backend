package ru.itis.rusteam.services;

import ru.itis.rusteam.dto.application.ApplicationDto;
import ru.itis.rusteam.dto.application.ApplicationsPage;
import ru.itis.rusteam.dto.application.NewOrUpdateApplicationDto;

public interface ApplicationsService {

    ApplicationsPage getAllApplications(Integer page, Double price, Double rating, String isNew, String category);

    ApplicationDto getApplicationById(Long id);

    ApplicationDto addApplication(NewOrUpdateApplicationDto application);

    ApplicationDto updateApplication(Long id, NewOrUpdateApplicationDto updatedApplication);

    void deleteApplication(Long id);

    ApplicationDto publishApplication(Long id);

    ApplicationsPage getAllApplicationsByDeveloperId(Long id, int page);

    ApplicationsPage getAllApplicationByContentString(int page, String content);
}
