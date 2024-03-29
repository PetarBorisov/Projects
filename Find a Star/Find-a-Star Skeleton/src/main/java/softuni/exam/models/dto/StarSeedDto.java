package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import softuni.exam.models.entity.Constellation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class StarSeedDto {


    @NotNull
    @Size(min = 6)
    @Expose
    private String description;
    @NotNull
    @Positive
    @Expose
    private Double lightYears;

    @NotNull
    @Size(min = 2,max = 30)
    @Expose
    private String name;
    @NotNull
    @Expose
    private String starType;
    @NotNull
    @Expose
    private Long constellation;

    public StarSeedDto() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLightYears() {
        return lightYears;
    }

    public void setLightYears(Double lightYears) {
        this.lightYears = lightYears;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStarType() {
        return starType;
    }

    public void setStarType(String starType) {
        this.starType = starType;
    }

    public Long getConstellation() {
        return constellation;
    }

    public void setConstellation(Long constellation) {
        this.constellation = constellation;
    }
}
