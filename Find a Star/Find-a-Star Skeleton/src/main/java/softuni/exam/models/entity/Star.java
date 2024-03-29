package softuni.exam.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "stars")
public class Star extends BaseEntity{



    private String name;
    private Double lightYears;
    private String description;
    private StarType starType;

    private Constellation constellation;

    public Star() {
    }
    @Column(name = "name",nullable = false,unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "light_years",nullable = false)
    public Double getLightYears() {
        return lightYears;
    }

    public void setLightYears(Double lightYears) {
        this.lightYears = lightYears;
    }
    @Column(columnDefinition = "TEXT",nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Column(name = "star_type",nullable = false)
    @Enumerated(EnumType.STRING)
    public StarType getStarType() {
        return starType;
    }

    public void setStarType(StarType starType) {
        this.starType = starType;
    }
@ManyToOne
@JoinColumn(name = "constellation_id")
    public Constellation getConstellation() {
        return constellation;
    }

    public void setConstellation(Constellation constellation) {
        this.constellation = constellation;
    }
}
