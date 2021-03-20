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
@Table(name = "receiver", schema = "", catalog = "")
public class ReceiverEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "receiver_id", nullable = false)
    private long receiverId;

    @Column(name = "send_message_id", nullable = false)
    private long sendMessageId;

    @Column(name = "receive_user_id", nullable = false)
    private long receiveUserId;

    @Column(name = "receiver_name")
    private String receiverName;

    @Column(name = "read", nullable = false)
    private boolean read;

    @Column(name = "receive_at")
    private java.sql.Timestamp receiveAt;

    @Column(name = "created_at", insertable = false, updatable = false)
    private java.sql.Timestamp createdAt;

    @Column(name = "created_user_id", nullable = false, updatable = false)
    private long createdUserId;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private java.sql.Timestamp updatedAt;

    @Column(name = "updated_user_id", nullable = false)
    private long updatedUserId;
}
