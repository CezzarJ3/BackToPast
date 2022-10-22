package cs.julia.backtopast.exhibitionpart;

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

    @Column(name = "exhibit_id", nullable = false)
    private int exhibitId;

    @Column(name = "exhibition_id", nullable = false)
    private int exhibitionId;
}
