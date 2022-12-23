package cs.julia.backtopast.exhibitionpart.domain;

import cs.julia.backtopast.exhibit.domain.Exhibit;
import cs.julia.backtopast.exhibition.domain.Exhibition;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "EXHIBITION_PART")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ExhibitionPart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "exhibit_id", referencedColumnName = "id", nullable = false)
    private Exhibit exhibit;

    @ManyToOne
    @JoinColumn(name = "exhibition_id", referencedColumnName = "id", nullable = false)
    private Exhibition exhibition;
}
