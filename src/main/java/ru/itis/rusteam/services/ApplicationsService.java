package ru.itis.rusteam.services;

import ru.itis.rusteam.dto.ApplicationDto;
import ru.itis.rusteam.dto.ApplicationsPage;
import ru.itis.rusteam.dto.NewOrUpdateApplicationDto;

public interface ApplicationsService {

    ApplicationsPage getAllApplications(int page);

    ApplicationDto addApplication(NewOrUpdateApplicationDto newApplication);

    ApplicationDto getApplication(Long applicationId);

    ApplicationDto updateApplication(Long applicationId, NewOrUpdateApplicationDto updatedApplication);

    void deleteApplication(Long applicationId);

    ApplicationDto publishApplication(Long applicationId);


}
