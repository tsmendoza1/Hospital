package com.example.prueba.model

import javax.persistence.*

@Entity
@Table(name = "tratamiento")

class Tratamiento {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var description: String? = null
    @Column(name="paciente_id")
    var pacienteId: Long? = null
}