
package com.gestion.agenda.repository;

import com.gestion.agenda.model.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactoRepositorio extends JpaRepository<Contacto, String> {
    
}
