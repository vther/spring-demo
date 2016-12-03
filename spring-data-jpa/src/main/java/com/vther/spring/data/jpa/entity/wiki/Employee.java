package com.vther.spring.data.jpa.entity.wiki;

import com.vther.spring.data.jpa.utils.DateUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "T_EMPLOYEE")
@SecondaryTable(name = "T_EMP_RELATION",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "EMP_ID", referencedColumnName = "ID")
)
public class Employee {

    @Id
//    @TableGenerator(name = "TABLE_GEN", table = "SEQUENCE_TABLE", pkColumnName = "SEQ_NAME",
//            valueColumnName = "SEQ_COUNT", pkColumnValue = "EMP_SEQ")
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Basic
    @Column(length = 100)
    private String firstName;
    // The @Basic is not required in general because it is the default.
    @Column(name = "LAST_NAME", length = 200)
    private String lastName;

    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Calendar startDate;

    @Column(name = "START_TIME")
    private Date startTime;

    @Column(name = "SALARY")
    private BigDecimal salary;

    // columnDefinition 手动指定这个字段的类型
    @Column(columnDefinition = "TIMESTAMP")
    private Date startTimeStamp;
    @Column(columnDefinition = "TIMESTAMP")
    private Date endTimeStamp;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MANAGER_ID", table = "T_EMP_RELATION", referencedColumnName = "ID")
    private Employee manager;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @Basic
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Convert(converter = BooleanTFConverter.class)
    private Boolean isActive;


    public enum Gender {
        MALE,
        FEMALE
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", startDate=").append(DateUtils.dateToString(startDate.getTime()));
        sb.append(", startTime=").append(DateUtils.dateToString(startTime));
        sb.append(", salary=").append(salary);
        sb.append(", startTimeStamp=").append(DateUtils.dateToString(startTimeStamp));
        sb.append(", endTimeStamp=").append(DateUtils.dateToString(endTimeStamp));
        sb.append(", manager=").append(manager);
        sb.append(", address=").append(address);
        sb.append(", gender=").append(gender);
        sb.append(", isActive=").append(isActive);
        sb.append('}');
        return sb.toString();
    }
}