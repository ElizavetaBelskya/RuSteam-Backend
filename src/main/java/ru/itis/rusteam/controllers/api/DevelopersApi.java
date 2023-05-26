package ru.itis.rusteam.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.rusteam.dto.application.ApplicationsPage;
import ru.itis.rusteam.models.account.Developer;

@Tags(value = {
        @Tag(name = "Applications")
})
@RequestMapping("/developers")
public interface DevelopersApi {
    @Operation(summary = "Получение списка приложений")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с приложениями",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ApplicationsPage.class))
                    })
    })
    @GetMapping("/{id}/applications")
    ResponseEntity<ApplicationsPage> getAllApplicationsByDeveloper(
            @Parameter(description = "Идентификатор разработчика", example = "42") @PathVariable("id") Long developerId,
            @Parameter(description = "Номер страницы", example = "1") @RequestParam("page") int page);
}
