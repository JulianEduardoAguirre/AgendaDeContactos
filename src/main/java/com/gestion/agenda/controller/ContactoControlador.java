package com.gestion.agenda.controller;

import com.gestion.agenda.model.Contacto;
import com.gestion.agenda.repository.ContactoRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactoControlador {
    
    @Autowired
    private ContactoRepositorio contactoRepositorio;
    
    
    @GetMapping({"/",""})
    public String inicio(Model modelo){
        List<Contacto> contactos = contactoRepositorio.findAll();
        modelo.addAttribute("contactos", contactos);
    
        return "index.html";
    }
    
    @GetMapping("/registrar")
    public String registrarContacto(Model modelo){
        modelo.addAttribute("contacto", new Contacto());
        
        return "formulario.html";
    }
    
    @PostMapping("/registrar")
    public String guardarContacto(@Validated Contacto contacto, BindingResult bindingResult, RedirectAttributes attr, Model modelo){
        if(bindingResult.hasErrors()){
            modelo.addAttribute("contacto", contacto);
        
            return "formulario.html";
        }
        
        contactoRepositorio.save(contacto);
        attr.addFlashAttribute("exito", "Contacto guardado con éxito");
        
        return "redirect:/";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, Model modelo){
        Contacto contacto = contactoRepositorio.findById(id).get();
        modelo.addAttribute("contacto", contacto);
        
        return "formulario.html";
    }
    
    @PostMapping("/editar/{id}")
    public String editar(@PathVariable String id, @Validated Contacto contacto, BindingResult bindingResult,
            RedirectAttributes attr, Model modelo){
        
        Contacto contactoDB = contactoRepositorio.findById(id).get();
        if(bindingResult.hasErrors()){
            modelo.addAttribute("contacto", contacto);
        
            return "formulario.html";
        }
        
        contactoDB.setNombre(contacto.getNombre());
        contactoDB.setApellido(contacto.getApellido());
        contactoDB.setEmail(contacto.getEmail());
        contactoDB.setTelefono(contacto.getTelefono());
        contactoDB.setFechaNacimiento(contacto.getFechaNacimiento());
        
        contactoRepositorio.save(contactoDB);
        attr.addFlashAttribute("exito", "Contacto modificado con éxito");
        
        return "redirect:/";
    }
    
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, RedirectAttributes attr){
        Contacto contacto = contactoRepositorio.findById(id).get();
        
        contactoRepositorio.delete(contacto);
        attr.addFlashAttribute("exito", "Contacto eliminado correctamente");
     
        return "redirect:/";
    }
}
