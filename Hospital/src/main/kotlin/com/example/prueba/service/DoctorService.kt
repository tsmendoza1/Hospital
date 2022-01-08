package com.example.prueba.service

import com.example.prueba.model.Doctor
import com.example.prueba.repository.DoctorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.server.ResponseStatusException

@Service
class DoctorService {
    @Autowired
    lateinit var doctorRepository: DoctorRepository


    fun list(): List<Doctor> {

        return doctorRepository.findAll()
    }


    fun save (@RequestBody doctor: Doctor): Doctor {
        try {

            if(doctor.nombre.equals("") || doctor.especialidad.equals("") ) {

                throw Exception( "Existen campos vacios")
            } else {
                return doctorRepository.save(doctor)
            }
        }  catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun update (@RequestBody doctor: Doctor): Doctor {
        try {

            val response = doctorRepository.findById(doctor.id)
                ?: throw Exception("El id ${doctor.id}  no existe")

            if (doctor.nombre.equals("") || doctor.nombre.equals("")
                || doctor.especialidad.equals("")){
                throw Exception( "Existen campos vacios")
            }

            else {
                return doctorRepository.save(doctor)
            }
        } catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)

        }
    }


    fun updateNombre (doctor: Doctor): Doctor {
        try {
            if (doctor.nombre.equals("")){
                throw Exception( "El campo Nombre no puede estar vacio")
            }

            val response = doctorRepository.findById(doctor.id)

                ?: throw Exception("El id ${doctor.id}  no existe")

            response.apply {
                this.nombre=doctor.nombre
            }
            return doctorRepository.save(response)

        }catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun delete (idDoctor:Long): Boolean{
        doctorRepository.deleteById(idDoctor)
        return true
    }
}