package pl.maciejdluzen.hotelreservation.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter @Setter
@EqualsAndHashCode(of = "id")
@ToString

@Entity
@Table(name = "ROOM_TYPES")
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(name = "guests_number", nullable = false)
    private Integer noPersons;
    @Column(name = "room_rate_net", nullable = false)
    private Double rateNet;
    @Column(nullable = false)
    private Double tax;
    @Column(name = "room_rate_gross", nullable = false)
    private Double rateGross;
    @Column(nullable = false)
    private String description;
    @Column(name = "feature_1")
    private String feature1;
    @Column(name = "feature_2")
    private String feature2;
    @Column(name = "feature_3")
    private String feature3;
    @Column(name = "feature_4")
    private String feature4;
    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] image;



}
