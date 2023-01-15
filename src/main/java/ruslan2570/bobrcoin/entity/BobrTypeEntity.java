package ruslan2570.bobrcoin.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bobrs_type")

public class BobrTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    private BigDecimal price;

    private BigDecimal bcPerMinute;

    private int lifetime;

    private String bonus;

    private String bonusDescription;

}
