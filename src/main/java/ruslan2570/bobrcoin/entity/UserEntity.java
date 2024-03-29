package ruslan2570.bobrcoin.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", indexes = {
        @Index(name = "idx_userentity_login_unq", columnList = "login", unique = true),
        @Index(name = "idx_userentity_email_unq", columnList = "email", unique = true)
})
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String login;

    private String password;

    private BigDecimal bcAmount;

    private BigDecimal bcPerMinute;

    private String email;

    private UUID emailConfirmation;

    private UUID passwordRestore;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<BobrEntity> bobrs;

    @ManyToMany
    @JoinTable(
        name = "user_achievement", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "achievement_id"))
    private List<AchievementEntity> achievements;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
