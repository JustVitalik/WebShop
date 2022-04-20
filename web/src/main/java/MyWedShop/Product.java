package MyWedShop;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "price")
    private int price;

    @NotNull
    @Column(name = "name")
    @Size(min = 2, max = 30)
    private String name;

    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "images")
    private byte[] images;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createData")
    private Date createData;
    @ManyToOne
    private User user;

    public Product() {
    }

    public Product(int price, String name, String description, byte[] images, Date createData) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.images = images;
        this.createData = createData;
    }

    public Product(int price, String name, String description) {
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImages() {
        return images;
    }

    public void setImages(byte[] images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateData() {
        return createData;
    }

    public void setCreateData(Date createData) {
        this.createData = createData;
    }
}
