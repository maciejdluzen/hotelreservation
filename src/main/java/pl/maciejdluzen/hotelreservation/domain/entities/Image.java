package pl.maciejdluzen.hotelreservation.domain.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"image"})
@NoArgsConstructor

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "content_type")
    private String contentType;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] image;
    @Column(name = "description")
    private String description;

}
