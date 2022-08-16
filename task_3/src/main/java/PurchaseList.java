import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "purchaselist")
@Getter
@Setter
public class PurchaseList {

    @EmbeddedId
    private PurchaseListKey id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_name", referencedColumnName = "name", insertable = false, updatable = false)
    Student student;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "course_name", referencedColumnName = "name", insertable = false, updatable = false)
    Course course;

    private int price;

    @Column(name = "subscription_date")
    private LocalDateTime subscriptionDate;
}
