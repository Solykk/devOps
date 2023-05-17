package com.dev.ops.commons.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * @author Dmitriy Lyashenko
 */
@Entity
@Table(name = "main_requests", schema = "dev_ops")
public class MainRequest implements Serializable {
    @Id
    @GenericGenerator(
            name = "dev_ops.main_requests_ar_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "dev_ops.main_requests_ar_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dev_ops.main_requests_ar_id_seq")
    @Column(name = "mr_id")
    private Integer id;

    @Column(name = "mr_ip_address", nullable = false)
    private String ipAddress;

    @Column(name = "mr_message")
    private String message;

    @Column(name = "mr_created_date", nullable = false)
    private ZonedDateTime createdDate = ZonedDateTime.now();

    public MainRequest() {
    }

    public MainRequest(String ipAddress, String message) {
        this.ipAddress = ipAddress;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainRequest that = (MainRequest) o;
        return Objects.equals(id, that.id) && Objects.equals(ipAddress, that.ipAddress) && Objects.equals(message, that.message) && Objects.equals(createdDate, that.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ipAddress, message, createdDate);
    }

    @Override
    public String toString() {
        return "MainRequest{" +
                "id=" + id +
                ", ipAddress='" + ipAddress + '\'' +
                ", message='" + message + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }

    public record Dto(Integer id, String address, String message, ZonedDateTime created) {
        public Dto(MainRequest mainRequest) {
            this(mainRequest.id, mainRequest.ipAddress, mainRequest.message, mainRequest.createdDate);
        }
    }
}
