package org.example.entity

import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "driver")
class DriverEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var name: String? = null
    var login: String? = null
    var cpf: String? = null
    var cnh: String? = null

    @Column(name = "date_add")
    var dateAdd: Date? = null
    var status: Int = 1

    @Column(name = "account_id")
    var accountId: Int = 539

    @Column(name = "group_id")
    var groupId: Int = 15290

    @Column(name = "subgroup_id")
    var subgroupId: Int = 18367

    var auth: Int = 5
    constructor()

    constructor(name: String, login: String, cpf: String, cnh: String, date: Date){
        this.name = name
        this.login = login
        this.cpf = cpf
        this.cnh = cnh
        this.dateAdd = date
    }

}
