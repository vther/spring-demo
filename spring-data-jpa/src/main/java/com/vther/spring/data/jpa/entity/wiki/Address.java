package com.vther.spring.data.jpa.entity.wiki;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity(name = "T_ADDRESS")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer addressId;

    private String city;
    private String street;

    private String country;
    private String postCode;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEEID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Employee owner;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Address{");
        sb.append("addressId=").append(addressId);
        sb.append(", city='").append(city).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", postCode='").append(postCode).append('\'');
        sb.append(", owner=").append(owner);
        sb.append('}');
        return sb.toString();
    }
}
