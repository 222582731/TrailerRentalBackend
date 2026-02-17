package tr.ac.co.domain;

import jakarta.persistence.*;
import tr.ac.co.domain.enums.Status;
import tr.ac.co.domain.enums.TrailerType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "trailers")
public class Trailer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    private TrailerType type;
    private String description;
    @Column(nullable = false)
    private BigDecimal pricePerDay;
    @Column(nullable = false)
    private int capacityKg;
    private String size;
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime createdAt;

    //Automatically set the createdAt.
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if(status == null){
            status = Status.AVAILABLE;
        }
    }

    public Trailer() {

    }

    private Trailer(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.type = builder.type;
        this.description = builder.description;
        this.pricePerDay = builder.pricePerDay;
        this.capacityKg = builder.capacityKg;
        this.size = builder.size;
        this.imageUrl = builder.imageUrl;
        this.status = builder.status;
        this.createdAt = builder.createdAt;
    }

    //Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TrailerType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public int getCapacityKg() {
        return capacityKg;
    }

    public String getSize() {
        return size;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    //Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(TrailerType type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void setCapacityKg(int capacityKg) {
        this.capacityKg = capacityKg;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Trailer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", capacityKg=" + capacityKg +
                ", size='" + size + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }

    public static class Builder{
        private Long id;
        private String name;
        private TrailerType type;
        private String description;
        private BigDecimal pricePerDay;
        private int capacityKg;
        private String size;
        private String imageUrl;
        private Status status;
        private LocalDateTime createdAt;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }
        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        public Builder setType(TrailerType type) {
            this.type = type;
            return this;
        }
        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }
        public Builder setPricePerDay(BigDecimal pricePerDay) {
            this.pricePerDay = pricePerDay;
            return this;
        }
        public Builder setCapacityKg(int capacityKg) {
            this.capacityKg = capacityKg;
            return this;
        }
        public Builder setSize(String size) {
            this.size = size;
            return this;
        }
        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }
        public Builder setStatus(Status status) {
            this.status = status;
            return this;
        }
        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder copy(Trailer trailer){
            this.id = trailer.id;
            this.name = trailer.name;
            this.type = trailer.type;
            this.description = trailer.description;
            this.pricePerDay = trailer.pricePerDay;
            this.capacityKg = trailer.capacityKg;
            this.size = trailer.size;
            this.imageUrl = trailer.imageUrl;
            this.status = trailer.status;
            this.createdAt = trailer.createdAt;
            return this;
        }

        public Trailer build(){
            return new Trailer(this);
        }
    }
}
