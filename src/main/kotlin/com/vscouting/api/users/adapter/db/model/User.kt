package com.vscouting.api.users.adapter.db.model

import org.hibernate.annotations.Type
import java.time.LocalDate
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(columnDefinition = "CHAR(36)")
    var id: UUID,

    @Column(nullable = true, length = 100)
    var name: String? = null,

    @Column(nullable = true, length = 100)
    var surname: String? = null,

    @Column(name = "birth_date", nullable = true)
    var birthDate: LocalDate? = null,

    @Column(nullable = true, length = 100)
    var nationality: String? = null,

    @OneToOne(mappedBy = "user", cascade = [CascadeType.PERSIST], fetch = FetchType.LAZY)
    var credentials: UserCredentials? = null,

)
