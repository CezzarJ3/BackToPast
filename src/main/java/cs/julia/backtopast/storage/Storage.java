package cs.julia.backtopast.storage;

import cs.julia.backtopast.department.domain.Department;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "STORAGE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "manager", nullable = false)
    private String manager;

    @ManyToOne
    @JoinColumn(name = "type", nullable = false)
    private Department department;
}
