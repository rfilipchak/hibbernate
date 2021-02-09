package ua.mainacademy.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Builder(toBuilder = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@Entity
public class Order extends BaseEntity {

    private Integer id;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @Column(name = "creation_time", nullable = false)
    private Long creationTime;

    @Column(nullable = false)
    private Status status;

    public enum Status {
        OPEN,
        CLOSED
    }
}