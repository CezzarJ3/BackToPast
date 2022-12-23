package cs.julia.backtopast.exhibition.domain;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "EXHIBITION")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Exhibition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @DateTimeFormat(pattern = "dd-MM-yyy HH:mm")
    @Column(name = "start_date", nullable = false)
    private Timestamp start_date;

    @DateTimeFormat(pattern = "dd-MM-yyy HH:mm")
    @Column(name = "end_date", nullable = false)
    private Timestamp end_date;

    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Column(name = "place", nullable = false, length = 100)
    private String place;

    @Column(name = "organizer", length = 50)
    private String organizer;
}
