package ru.itis.rusteam.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.itis.rusteam.dto.application.ActionDates;
import ru.itis.rusteam.dto.application.ApplicationDto;
import ru.itis.rusteam.dto.application.ApplicationsPage;
import ru.itis.rusteam.dto.application.NewOrUpdateApplicationDto;
import ru.itis.rusteam.models.Application;
import ru.itis.rusteam.models.Category;
import ru.itis.rusteam.models.account.Developer;
import ru.itis.rusteam.repositories.ApplicationsRepository;
import ru.itis.rusteam.repositories.CategoryRepository;
import ru.itis.rusteam.repositories.DevelopersRepository;
import ru.itis.rusteam.services.ApplicationsService;

import java.time.LocalDateTime;
import java.util.*;

import static ru.itis.rusteam.dto.application.ApplicationDto.from;
import static ru.itis.rusteam.utils.ServicesUtils.getOrThrow;

@RequiredArgsConstructor
@Service
public class ApplicationServiceImpl implements ApplicationsService {

    private final ApplicationsRepository applicationsRepository;

    private final DevelopersRepository developersRepository;

    private final CategoryRepository categoryRepository;

    @Value("${default.page-size}")
    private int defaultPageSize;

    @Override
    public ApplicationsPage getAllApplicationByContentString(int page, String content) {
        List<Application> apps = applicationsRepository.findAllByContent("%" + content + "%");
        return ApplicationsPage.builder()
                .applications(ApplicationDto.from(apps))
                .totalPagesCount(apps.size())
                .build();
    }


    @Override
    public ApplicationsPage getAllApplications(Integer page, Double price, Double rating, String isNew, String category) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize);
        Collection<Category> categories = Collections.emptyList();

        Optional<Category> categoryToSearch;
        if (category != null) {
            categoryToSearch = categoryRepository.findByName(category);
            if (categoryToSearch.isPresent()) {
                categories = Collections.singleton(categoryToSearch.get());
            }
        } else {
            categoryToSearch = Optional.empty();
        }

        LocalDateTime withinMonth = null;
        if (isNew == null || !isNew.equals("true")) {
            withinMonth = LocalDateTime.of(1970, 1, 1, 0, 0);
        } else {
            withinMonth = LocalDateTime.now().minusDays(30);
        }
        if (rating == null) {
            rating = 0.0;
        }

        if (price == null) {
            price = Double.valueOf(-1);
        }
        Page<Application> page1 = null;
        if (price != 0.0 && categoryToSearch.isEmpty()) {
            page1 = applicationsRepository.findAllByPublishDateAndRating(pageRequest, withinMonth, rating);
        } else if (price == 0.0 && categoryToSearch.isEmpty()) {
            page1 = applicationsRepository.findAllByPublishDateAndRatingAndFree(pageRequest, withinMonth, rating);
        } else if (price == 0.0 && categoryToSearch.isPresent()) {
            page1 = applicationsRepository.findAllByPublishDateAndRatingAndFreeAndCategories(pageRequest, withinMonth, rating, categories);
        } else {
            page1 = applicationsRepository.findAllByPublishDateAndRatingAndCategories(pageRequest, withinMonth, rating, categories);
        }

        return ApplicationsPage.builder()
                .applications(ApplicationDto.from(page1.getContent()))
                .totalPagesCount(page1.getTotalPages())
                .build();
    }

    @Override
    public ApplicationDto addApplication(NewOrUpdateApplicationDto application) {
        Application applicationToSave = Application.builder()
                .name(application.getName())
                .description(application.getDescription())
                .developer(getDeveloperOrThrow(application.getDeveloperId()))
                .price(application.getPrice())
                .rating(0.0)
                .categories(application.getCategories())
                .dates(ActionDates.builder()
                        .publishDate(LocalDateTime.now())
                        .modificationDate(LocalDateTime.now())
                        .build())
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
        applicationForUpdate.setDescription(updatedApplication.getDescription());
        applicationForUpdate.setDeveloper(getDeveloperOrThrow(updatedApplication.getDeveloperId()));
        applicationForUpdate.setCategories(updatedApplication.getCategories());
        ActionDates dates = ActionDates.builder()
                .publishDate(applicationForUpdate.getDates().getPublishDate())
                .modificationDate(LocalDateTime.now())
                .build();
        applicationForUpdate.setDates(dates);

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

    @Override
    public ApplicationsPage getAllApplicationsByDeveloperId(Long id, int page) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize);
        Developer developer = getDeveloperOrThrow(id);
        Page<Application> applicationsPage = applicationsRepository.findAllByDeveloper(pageRequest, developer);

        return ApplicationsPage.builder()
                .applications(from(applicationsPage.getContent()))
                .totalPagesCount(applicationsPage.getTotalPages())
                .build();
    }


    private Application getApplicationOrThrow(Long id) {
        return getOrThrow(id, applicationsRepository, "Application");
    }

    private Developer getDeveloperOrThrow(Long id) {
        return getOrThrow(id, developersRepository, "Developer");
    }
}
