package com.prototype.hibernate.model.example;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.sql.Timestamp;
import java.util.*;
import javax.persistence.*;

@Table(name = "account")
@Entity
public class AccountJava {

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private UUID uuid;

    @Column(nullable = false)
    private Boolean isActive = false;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    public UUID getUuid() {
        return uuid;
    }

    public Boolean getActive() {
        return isActive;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "AccountJava{" +
                "uuid=" + uuid +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
