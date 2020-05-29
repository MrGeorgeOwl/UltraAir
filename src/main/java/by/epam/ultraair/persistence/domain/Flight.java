package by.epam.ultraair.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "flight", catalog = "ultraAir")
public class Flight extends BaseEntity{
    @Column(name = "fromPlace")
    private String fromPlace;

    @Column(name = "toPlace")
    private String toPlace;

    @Column(name = "departureDate")
    private Date departureDate;

    @Column(name = "arrivalDate")
    private Date arrivalDate;

    public Flight(){}

    public Flight(Integer id, String fromPlace, String toPlace, Date departureDate, Date arrivalDate) {
        super.setId(id);
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    public Flight(String fromPlace, String toPlace, Date departureDate, Date arrivalDate) {
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getFromPlace() {
        return fromPlace;
    }

    public void setFromPlace(String fromPlace) {
        this.fromPlace = fromPlace;
    }

    public String getToPlace() {
        return toPlace;
    }

    public void setToPlace(String toPlace) {
        this.toPlace = toPlace;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "fromPlace='" + fromPlace + '\'' +
                ", toPlace='" + toPlace + '\'' +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                '}';
    }
}
