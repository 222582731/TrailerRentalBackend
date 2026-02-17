package tr.ac.co.domain;

import jakarta.persistence.*;
import tr.ac.co.domain.enums.BookingStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Many bookings can belong to one user
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    //Each trailer can have many bookings
    @ManyToOne(optional = false)
    @JoinColumn(name = "trailer_id")
    private Trailer trailer;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    private BigDecimal totalCost;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (status == null) {
            status = BookingStatus.PENDING;
        }
    }

    public Booking() {}

    private Booking(Builder builder) {
        this.id = builder.id;
        this.user = builder.user;
        this.trailer = builder.trailer;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.totalCost = builder.totalCost;
        this.status = builder.status;
        this.createdAt = builder.createdAt;
    }

    // Getters
    public Long getId() {
        return id;
    }
    public User getUser() {
        return user;
    }
    public Trailer getTrailer() {
        return trailer;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public BigDecimal getTotalCost() {
        return totalCost;
    }
    public BookingStatus getStatus() {
        return status;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setUser(User user) {
        this.user = user;
    }
    public void setTrailer(Trailer trailer) {
        this.trailer = trailer;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
    public void setStatus(BookingStatus status) {
        this.status = status;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", user=" + (user != null ? user.getId() : null) +
                ", trailer=" + (trailer != null ? trailer.getId() : null) +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalCost=" + totalCost +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }

    public static class Builder {
        private Long id;
        private User user;
        private Trailer trailer;
        private LocalDate startDate;
        private LocalDate endDate;
        private BigDecimal totalCost;
        private BookingStatus status;
        private LocalDateTime createdAt;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setTrailer(Trailer trailer) {
            this.trailer = trailer;
            return this;
        }

        public Builder setStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder setEndDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder setTotalCost(BigDecimal totalCost) {
            this.totalCost = totalCost;
            return this;
        }

        public Builder setStatus(BookingStatus status) {
            this.status = status;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder copy(Booking booking) {
            this.id = booking.id;
            this.user = booking.user;
            this.trailer = booking.trailer;
            this.startDate = booking.startDate;
            this.endDate = booking.endDate;
            this.totalCost = booking.totalCost;
            this.status = booking.status;
            this.createdAt = booking.createdAt;
            return this;
        }

        public Booking build() {
            return new Booking(this);
        }
    }
}
