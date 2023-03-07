package ru.itis.rusteam.services;

import ru.itis.rusteam.dto.application.ApplicationDto;
import ru.itis.rusteam.dto.application.ApplicationsPage;
import ru.itis.rusteam.dto.application.NewOrUpdateApplicationDto;

public interface ApplicationsService {

    ApplicationsPage getAllApplications(int page);

    ApplicationDto addApplication(NewOrUpdateApplicationDto newApplication);

    ApplicationDto getApplication(Long applicationId);

    ApplicationDto updateApplication(Long applicationId, NewOrUpdateApplicationDto updatedApplication);

    void deleteApplication(Long applicationId);

    ApplicationDto publishApplication(Long applicationId);


}
