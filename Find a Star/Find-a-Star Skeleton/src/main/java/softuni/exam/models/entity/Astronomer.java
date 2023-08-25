package softuni.exam.models.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "astronomers")
public class Astronomer extends BaseEntity{


    private String firstName;
    private String lastName;
    private Double salary;
    private Double 	averageObservationHours;
    private Date birthday;
    private Star star;

    public Astronomer() {
    }
    @Column(name = "first_name",nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(name = "last_name",nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Column(name = "salary",nullable = false)
    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
    @Column(name = "average_observation_hours",nullable = false)
    public Double getAverageObservationHours() {
        return averageObservationHours;
    }

    public void setAverageObservationHours(Double averageObservationHours) {
        this.averageObservationHours = averageObservationHours;
    }
    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @ManyToOne
    @JoinColumn(name = "observing_star_id")
    public Star getStar() {
        return star;
    }

    public void setStar(Star star) {
        this.star = star;
    }
}
