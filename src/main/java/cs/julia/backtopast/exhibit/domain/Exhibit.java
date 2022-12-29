package cs.julia.backtopast.exhibit.domain;

import cs.julia.backtopast.department.domain.Department;
import cs.julia.backtopast.storage.domain.Storage;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "EXHIBIT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    @Column(name = "year")
    private int year;

    @Column(name = "author")
    private String author;

    @ManyToOne
    @JoinColumn(name = "type",referencedColumnName = "id", nullable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "store", referencedColumnName = "id")
    private Storage storage;
}
