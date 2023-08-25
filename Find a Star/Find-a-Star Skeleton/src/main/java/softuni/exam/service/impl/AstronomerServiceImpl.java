package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AstronomerSeedDto;
import softuni.exam.models.dto.AstronomerSeedRootDto;
import softuni.exam.models.entity.Astronomer;
import softuni.exam.models.entity.Star;
import softuni.exam.repository.AstronomerRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.AstronomerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class AstronomerServiceImpl implements AstronomerService {
    public static String ASTRONOMER_FILE_PATH = "src/main/resources/files/xml/astronomers.xml";

    private final AstronomerRepository astronomerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;
    private final StarRepository starRepository;



@Autowired
    public AstronomerServiceImpl(AstronomerRepository astronomerRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser, StarRepository starRepository) {
        this.astronomerRepository = astronomerRepository;
    this.modelMapper = modelMapper;
    this.validationUtil = validationUtil;
    this.xmlParser = xmlParser;
    this.starRepository = starRepository;
}


    @Override
    public boolean areImported() {
        return astronomerRepository.count() > 0;
    }

    @Override
    public String readAstronomersFromFile() throws IOException {
        return Files.readString(Path.of(ASTRONOMER_FILE_PATH));
    }

    @Override
    public String importAstronomers() throws IOException, JAXBException {
    StringBuilder stringBuilder = new StringBuilder();

  final List<AstronomerSeedDto> astronomers =
          this.xmlParser.fromFile(ASTRONOMER_FILE_PATH,AstronomerSeedRootDto.class)
                  .getAstronomers();

        for (AstronomerSeedDto astronomer : astronomers) {
            stringBuilder.append(System.lineSeparator());

            if (this.validationUtil.isValid(astronomer)) {
                final Optional<Astronomer> astronomer1 = this.astronomerRepository.findFirstByFirstNameAndLastName(astronomer.getFirstName(),
                        astronomer.getLastName());
                final Optional<Star> star = this.starRepository.findById(astronomer.getObservingStarId());

                if (astronomer1.isPresent() || star.isEmpty()) {
                    stringBuilder.append(String.format("Invalid astronomer"));
                    continue;
                }

                final Astronomer astronomerToSave = this.modelMapper.map(astronomer, Astronomer.class);
                astronomerToSave.setStar(star.get());

                this.astronomerRepository.save(astronomerToSave);
                stringBuilder.append(String.format(Locale.US,"Successfully imported astronomer %s %s - %.2f",
                        astronomer.getFirstName(), astronomer.getLastName(), astronomer.getAverageObservationHours()));
                continue;
            }
            stringBuilder.append(String.format("Invalid astronomer"));
        }
   return stringBuilder.toString().trim();
    }
}
