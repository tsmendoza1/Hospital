package com.example.prueba.service

import com.example.prueba.model.Paciente
import com.example.prueba.model.Tratamiento
import com.example.prueba.repository.PacienteRepository
import com.example.prueba.repository.TratamientoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.server.ResponseStatusException


@Service
class TratamientoService {

    @Autowired
    lateinit var tratamientoRepository: TratamientoRepository
    @Autowired
    lateinit var pacienteRepository: PacienteRepository


    fun list(): List<Tratamiento> {

        return tratamientoRepository.findAll()

    }

    fun save (@RequestBody tratamiento: Tratamiento): Tratamiento {

        try {

            val response = pacienteRepository.findById(tratamiento.pacienteId)
                ?: throw Exception("El id ${tratamiento.pacienteId}  no existe")

            if(tratamiento.description.equals("") ) {
                throw Exception("Existen campos vacios")
            }
            else {
                return tratamientoRepository.save(tratamiento)
            }

        }   catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)

        }
    }

    fun update (@RequestBody tratamiento: Tratamiento): Tratamiento {

        try {

            val response = tratamientoRepository.findById(tratamiento.pacienteId)
                ?: throw Exception("El id ${tratamiento.pacienteId}  no existe")


            val response1 = tratamientoRepository.findById(tratamiento.id)
                ?: throw Exception("El id ${tratamiento.id}  no existe")

            if (tratamiento.description.equals("") ) {
                throw Exception("Existen campos vacios")
            }

            else{
                return tratamientoRepository.save(response)
            }

        } catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }
}