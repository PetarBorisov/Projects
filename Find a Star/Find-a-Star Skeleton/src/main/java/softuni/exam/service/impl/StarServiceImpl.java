package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.StarSeedDto;
import softuni.exam.models.entity.Astronomer;
import softuni.exam.models.entity.Star;
import softuni.exam.repository.AstronomerRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.StarService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class StarServiceImpl implements StarService {
    public static String STAR_FILE_PATH = "src/main/resources/files/json/stars.json";

    private final StarRepository starRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final AstronomerRepository astronomerRepository;


@Autowired
    public StarServiceImpl(StarRepository starRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson, AstronomerRepository astronomerRepository) {
        this.starRepository = starRepository;
    this.modelMapper = modelMapper;
    this.validationUtil = validationUtil;
    this.gson = gson;
    this.astronomerRepository = astronomerRepository;
}


    @Override
    public boolean areImported() {
        return starRepository.count() > 0;
    }

    @Override
    public String readStarsFileContent() throws IOException {
        return Files.readString(Path.of(STAR_FILE_PATH));
    }

    @Override
    public String importStars() throws IOException {
    StringBuilder stringBuilder = new StringBuilder();

        Arrays.stream(gson.fromJson(readStarsFileContent(), StarSeedDto[].class))
                .filter(starSeedDto -> {
                    boolean isValid = validationUtil.isValid(starSeedDto);
                    if (starRepository.findFirstByName(starSeedDto.getName()).isPresent()) {
                        isValid = false;
                    }
                    stringBuilder.append(isValid ? String.format(Locale.US,"Successfully imported star %s - %.2f light years",
                            starSeedDto.getName(),starSeedDto.getLightYears())
                            : "Invalid star").append(System.lineSeparator()) ;
                    return isValid;

                })
                .map(starSeedDto -> modelMapper.map(starSeedDto, Star.class))
                .forEach(starRepository::save);
        ;

        return stringBuilder.toString();
    }

    @Override
    public String exportStars() {
        StringBuilder sb = new StringBuilder();

        List<Astronomer> allAstronomers = astronomerRepository.findAll();
        List<Star> stars = starRepository.allStars();


        for (int i = 0; i < stars.size(); i++) {
            for (int j = 0; j <allAstronomers.size() ; j++) {
                if(stars.get(i).getId() == allAstronomers.get(j).getStar().getId()) {
                    stars.remove(stars.get(i));
                    i--;
                }
            }
        }

        stars.forEach(star -> sb.append(String.format("Star: %s",star.getName())).append(System.lineSeparator()
                ).append(String.format("   *Distance: %s light years",star.getLightYears()))
                .append(System.lineSeparator())
                .append(String.format("   **Description: %s",star.getDescription()))
                .append(System.lineSeparator())
                .append(String.format("   ***Constellation: %s",star.getConstellation().getName()))
                .append(System.lineSeparator()));

        return sb.toString().trim();
    }
}
