package ru.itis.rusteam.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.itis.rusteam.dto.application.ApplicationDto;
import ru.itis.rusteam.dto.application.ApplicationsPage;
import ru.itis.rusteam.dto.application.NewOrUpdateApplicationDto;
import ru.itis.rusteam.exceptions.NotFoundException;
import ru.itis.rusteam.models.Application;
import ru.itis.rusteam.models.account.Account;
import ru.itis.rusteam.models.account.Developer;
import ru.itis.rusteam.repositories.ApplicationsRepository;
import ru.itis.rusteam.services.ApplicationsService;

import static ru.itis.rusteam.dto.application.ApplicationDto.from;

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
    public ApplicationDto addApplication(NewOrUpdateApplicationDto application) {
        Application applicationToSave = Application.builder()
                .name(application.getName())
                .developer(getDeveloper(application))
                .state(Application.State.DRAFT)
                .build();

        applicationsRepository.save(applicationToSave);

        return from(applicationToSave);
    }

    @Override
    public ApplicationDto getApplicationById(Long applicationId) {
        Application application = getApplicationOrThrow(applicationId);
        return from(application);
    }

    @Override
    public ApplicationDto updateApplication(Long applicationId, NewOrUpdateApplicationDto updatedApplication) {
        Application applicationForUpdate = getApplicationOrThrow(applicationId);

        applicationForUpdate.setName(updatedApplication.getName());
        applicationForUpdate.setDeveloper(getDeveloper(updatedApplication));

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

    private Developer getDeveloper(NewOrUpdateApplicationDto application) {
        return Developer.builder().account(Account.builder().id(application.getDeveloperId()).build()).build();
    }
}
