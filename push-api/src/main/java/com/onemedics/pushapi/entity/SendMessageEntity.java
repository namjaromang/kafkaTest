package com.onemedics.pushapi.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "send_message", schema = "", catalog = "")
public class SendMessageEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "send_message_id", nullable = false)
    private long sendMessageId;

    @Column(name = "send_user_id")
    private long sendUserId;

    @Column(name = "sender_name")
    private String senderName;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "data")
    private String data;

    @Column(name = "send_at", nullable = false)
    private java.sql.Timestamp sendAt;

    @Column(name = "send_reservation_at")
    private java.sql.Timestamp sendReservationAt;

    @Column(name = "send_success", nullable = false)
    private long sendSuccess;

    @Column(name = "created_at", insertable = false, updatable = false)
    private java.sql.Timestamp createdAt;

    @Column(name = "created_user_id", nullable = false, updatable = false)
    private long createdUserId;
}
