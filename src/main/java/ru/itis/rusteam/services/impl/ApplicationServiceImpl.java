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
import ru.itis.rusteam.models.account.Developer;
import ru.itis.rusteam.repositories.ApplicationsRepository;
import ru.itis.rusteam.repositories.DevelopersRepository;
import ru.itis.rusteam.services.ApplicationsService;

import static ru.itis.rusteam.dto.application.ApplicationDto.from;

@RequiredArgsConstructor
@Service
public class ApplicationServiceImpl implements ApplicationsService {

    private final ApplicationsRepository applicationsRepository;

    private final DevelopersRepository developersRepository;

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
                .developer(getDeveloperOrThrow(application.getDeveloperId()))
                .state(Application.State.DRAFT)
                .build();

        //TODO - сделать проверку корректности данных
        applicationsRepository.save(applicationToSave);

        return from(applicationToSave);
    }

    @Override
    public ApplicationDto getApplicationById(Long id) {
        return from(getApplicationOrThrow(id));
    }

    @Override
    public ApplicationDto updateApplication(Long id, NewOrUpdateApplicationDto updatedApplication) {
        Application applicationForUpdate = getApplicationOrThrow(id);

        applicationForUpdate.setName(updatedApplication.getName());
        applicationForUpdate.setDeveloper(getDeveloperOrThrow(updatedApplication.getDeveloperId()));

        //TODO - сделать проверку корректности данных
        applicationsRepository.save(applicationForUpdate);

        return from(applicationForUpdate);
    }

    @Override
    public void deleteApplication(Long id) {
        Application applicationForDelete = getApplicationOrThrow(id);

        applicationForDelete.setState(Application.State.DELETED);

        applicationsRepository.save(applicationForDelete);
    }

    @Override
    public ApplicationDto publishApplication(Long id) {
        Application applicationForPublish = getApplicationOrThrow(id);

        applicationForPublish.setState(Application.State.ACTIVE);

        applicationsRepository.save(applicationForPublish);

        return from(applicationForPublish);
    }


    private Application getApplicationOrThrow(Long id) {
        return applicationsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Приложение с идентификатором <" + id + "> не найдено"));
    }

    private Developer getDeveloperOrThrow(Long id) {
        //TODO - тут, наверное, стоит сделать проверку, что разработчик вообще существует
        return developersRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Разработчик с идентификатором <" + id + "> не найден"));
    }
}
