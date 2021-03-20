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
@Table(name = "user_token", schema = "", catalog = "")
public class UserTokenEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "user_token_id", nullable = false)
    private long userTokenId;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "phone")
    private String phone;

    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @Column(name = "withdraw")
    private Boolean withdraw;

    @Column(name = "device_token", nullable = false)
    private String deviceToken;

    @Column(name = "client_id", nullable = false)
    private String clientId;

    @Column(name = "device_type", nullable = false)
    private String deviceType;

    @Column(name = "device_model")
    private String deviceModel;

    @Column(name = "client_version")
    private String clientVersion;

    @Column(name = "push_enabled", nullable = false)
    private boolean pushEnabled;

    @Column(name = "created_at", insertable = false, updatable = false)
    private java.sql.Timestamp createdAt;

    @Column(name = "created_user_id", nullable = false, updatable = false)
    private long createdUserId;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private java.sql.Timestamp updatedAt;

    @Column(name = "updated_user_id", nullable = false)
    private long updatedUserId;
}
