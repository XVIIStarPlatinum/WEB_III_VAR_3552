package example.entity;

import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "point_model", schema = "s372799")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ResultEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence-generator")
    @SequenceGenerator(name = "sequence-generator", sequenceName = "lab3_x_test_table_id_seq", allocationSize = 1)
    private long id;
    private int x;
    private double y;
    private double r;
    private boolean hitResult;
}
