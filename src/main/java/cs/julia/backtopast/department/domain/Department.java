package cs.julia.backtopast.department.domain;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "DEPARTMENT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;
}
