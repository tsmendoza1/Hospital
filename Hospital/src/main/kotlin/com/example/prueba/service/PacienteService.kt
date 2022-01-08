package com.example.prueba.service

import com.example.prueba.model.Paciente
import com.example.prueba.repository.DoctorRepository
import com.example.prueba.repository.PacienteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.server.ResponseStatusException

@Service
class PacienteService {

    @Autowired
    lateinit var pacienteRepository: PacienteRepository

    @Autowired
    lateinit var doctorRepository: DoctorRepository

    fun list(): List<Paciente> {

        return pacienteRepository.findAll()

    }

    fun save (@RequestBody paciente: Paciente): Paciente {

        try {

            val response = doctorRepository.findById(paciente.doctorId)
                ?: throw Exception("El Id ${paciente.doctorId}  no existe")

            if(paciente.nombre.equals("") || paciente.edad.equals("") ) {
                throw Exception("Existen campos vacios")
            }
            else {
                return pacienteRepository.save(paciente)
            }

        }   catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)

        }
    }

    fun update (@RequestBody paciente: Paciente): Paciente {

        try {

            val response = pacienteRepository.findById(paciente.doctorId)
                ?: throw Exception("El id   ${paciente.doctorId}  no existe")


            val response1 = pacienteRepository.findById(paciente.id)
                ?: throw Exception("El id ${paciente.id}  no existe")

            if (paciente.nombre.equals("") || paciente.edad.equals("") ) {
                throw Exception("Existen campos vacios")
            }

            else{
                return pacienteRepository.save(paciente)
            }

        } catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }


    fun updateNombre (paciente: Paciente):Paciente {
        try {
            if (paciente.nombre.equals("")){
                throw Exception(" El campo Apellido esta vacio")
            }

            val response = pacienteRepository.findById(paciente.id)
                ?: throw Exception("El ID ${paciente.id}  no existe")


            response.apply {
                this.nombre=paciente.nombre
            }
            return pacienteRepository.save(response)

        } catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun delete (idPaciente:Long): Boolean{
        pacienteRepository.deleteById(idPaciente)
        return true
    }
}