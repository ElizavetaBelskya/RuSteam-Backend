package ru.itis.rusteam.services;


import ru.itis.rusteam.dto.account.developer.DeveloperDto;
import ru.itis.rusteam.dto.account.developer.NewOrUpdateDeveloperDto;

public interface DevelopersService {

    DeveloperDto getDeveloperById(Long id);

    DeveloperDto addDeveloper(NewOrUpdateDeveloperDto developer);

    DeveloperDto updateDeveloper(Long id, NewOrUpdateDeveloperDto updatedDeveloper);

    void deleteDeveloper(Long id);
}
