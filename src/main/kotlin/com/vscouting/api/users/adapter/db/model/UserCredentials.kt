package com.vscouting.api.users.adapter.db.model

import org.hibernate.annotations.Type
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "user_credentials")
data class UserCredentials(

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(columnDefinition = "CHAR(36)")
    var id: UUID? = null,

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
    var user: User?,

    @Column(unique = true, length = 100)
    var username: String? = null,

    @Column(unique = true, length = 255)
    var email: String? = null,

    @Column(name = "password", nullable = true, length = 255)
    var password: String? = null,

    @Column(name = "last_login_at")
    var lastLoginAt: LocalDateTime? = null,

    @Column(name = "login_attempts")
    var loginAttempts: Int = 0,

    @Column(name = "status")
    var status: Status = Status.ACTIVE,

    @Column(name = "phone_number")
    var phoneNumber: String? = null,
)
