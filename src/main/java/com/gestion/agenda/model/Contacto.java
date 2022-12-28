/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.agenda.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 *
 * @author jaguirre89
 */
@Entity
public class Contacto {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @NotBlank(message = "Debe ingresar un nombre")
    private String nombre;
    
    @NotBlank(message = "Debe ingresar un nombre")
    private String apellido;

    @NotEmpty(message = "Debe ingresar una dirección de correo")
    @Email
    private String email;
    
    @NotNull(message = "Debe ingresar un número de contacto")
    private Integer telefono;
    
    @DateTimeFormat(iso = ISO.DATE)
    @Past
    @NotNull(message = "Debe ingresar su fecha de naciemiento")
    private LocalDate fechaNacimiento;
    
    private LocalDateTime fechaRegistro;

    public Contacto() {
    }

    public String getId() {
        return id;
    }
    
    public void setId(String id){
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    @PrePersist
    public void asignarFechaRegistro(){
        fechaRegistro = LocalDateTime.now();
    }
    
}
