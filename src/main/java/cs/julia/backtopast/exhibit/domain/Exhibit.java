package cs.julia.backtopast.exhibit.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "EXHIBIT")
@Data
@Accessors(chain = true)
public class Exhibit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "author")
    private String author;

    @Column(name = "hall_number")
    private int hall_number;
}
