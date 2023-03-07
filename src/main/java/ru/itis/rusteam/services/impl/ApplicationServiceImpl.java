package ru.itis.rusteam.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.itis.rusteam.dto.ApplicationDto;
import ru.itis.rusteam.dto.ApplicationsPage;
import ru.itis.rusteam.dto.NewOrUpdateApplicationDto;
import ru.itis.rusteam.exceptions.NotFoundException;
import ru.itis.rusteam.models.Application;
import ru.itis.rusteam.repositories.ApplicationsRepository;
import ru.itis.rusteam.services.ApplicationsService;

import static ru.itis.rusteam.dto.ApplicationDto.from;

@RequiredArgsConstructor
@Service
public class ApplicationServiceImpl implements ApplicationsService {

    private final ApplicationsRepository applicationsRepository;

    @Value("${default.page-size}")
    private int defaultPageSize;


    @Override
    public ApplicationsPage getAllApplications(int page) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize);
        Page<Application> applicationsPage = applicationsRepository.findAllByStateOrderById(pageRequest, Application.State.ACTIVE);

        return ApplicationsPage.builder()
                .applications(from(applicationsPage.getContent()))
                .totalPagesCount(applicationsPage.getTotalPages())
                .build();
    }

    @Override
    public ApplicationDto addApplication(NewOrUpdateApplicationDto newApplication) {
        Application application = Application.builder()
                .name(newApplication.getName())
                .companyId(newApplication.getCompanyId())
                .state(Application.State.DRAFT)
                .build();

        applicationsRepository.save(application);

        return from(application);
    }

    @Override
    public ApplicationDto getApplication(Long applicationId) {
        Application application = getApplicationOrThrow(applicationId);
        return from(application);
    }

    @Override
    public ApplicationDto updateApplication(Long applicationId, NewOrUpdateApplicationDto updatedApplication) {
        Application applicationForUpdate = getApplicationOrThrow(applicationId);

        applicationForUpdate.setName(updatedApplication.getName());
        applicationForUpdate.setCompanyId(updatedApplication.getCompanyId());

        applicationsRepository.save(applicationForUpdate);

        return from(applicationForUpdate);
    }

    @Override
    public void deleteApplication(Long applicationId) {
        Application applicationForDelete = getApplicationOrThrow(applicationId);

        applicationForDelete.setState(Application.State.DELETED);

        applicationsRepository.save(applicationForDelete);
    }

    @Override
    public ApplicationDto publishApplication(Long applicationId) {
        Application applicationForPublish = getApplicationOrThrow(applicationId);

        applicationForPublish.setState(Application.State.ACTIVE);

        applicationsRepository.save(applicationForPublish);

        return from(applicationForPublish);
    }


    private Application getApplicationOrThrow(Long applicationId) {
        return applicationsRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException("Приложение с идентификатором <" + applicationId + "> не найдено"));
    }
}
