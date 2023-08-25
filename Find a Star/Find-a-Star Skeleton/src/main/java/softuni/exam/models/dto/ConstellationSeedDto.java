package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ConstellationSeedDto {

    @NotNull
    @Size(min = 3,max = 20)
    @Expose
    private String name;

    @NotNull
    @Size(min = 5)
    @Expose
    private String description;

    public ConstellationSeedDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
