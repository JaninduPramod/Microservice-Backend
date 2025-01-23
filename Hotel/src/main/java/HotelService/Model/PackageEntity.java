package HotelService.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="package")
public class PackageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "package_id")
    int packageId;

    @Column(name = "package_name")
    private String packageName;

    @Column(name = "package_price")
    private int packagePrice;

    @Column(name = "package_details")
    private String packageDetails;

}
