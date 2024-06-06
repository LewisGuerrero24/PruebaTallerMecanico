




package com.example.tallerMecanico.App.WebController;

import com.example.tallerMecanico.App.Entity.*;
import com.example.tallerMecanico.App.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/reparaciones")
public class ReparacionesWebController {
    @Autowired
    private ReparacionRepository reparacionRepository;

    @Autowired
    private MecanicoRepository mecanicoRepository;

    @Autowired
    private RepuestoRepository repuestoRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/mecanico/{id}")
    public String viewmecanico(@PathVariable String id,Model model) {
        Optional<User> user = userRepository.findById(id);
        List<Reparacion> reparaciones = reparacionRepository.findByMecanico(user.get());
        model.addAttribute("IdUser",id);
        model.addAttribute("reparaciones", reparaciones);
        return "ViewMecanico";
    }
    @GetMapping
    public String getAllReparaciones(Model model) {
        List<Reparacion> reparaciones = reparacionRepository.findAll();
        model.addAttribute("reparaciones", reparaciones);
        return "ListReparaciones";
    }

    @GetMapping("/new/{id}")
    public String newReparacionMecanicoForm(@PathVariable String id,Model model) {
        model.addAttribute("reparacion", new Reparacion());
        model.addAttribute("mecanicos", userRepository.findById(id).get());
        model.addAttribute("repuestos", repuestoRepository.findAll());
        model.addAttribute("vehiculos", vehiculoRepository.findAll());
        return "reparacion_form_mecanico";
    }
    @GetMapping("/new")
    public String newReparacionForm(Model model) {
        model.addAttribute("reparacion", new Reparacion());
        model.addAttribute("mecanicos", userRepository.findByRol(Rol.MECANICO));
        model.addAttribute("repuestos", repuestoRepository.findAll());
        model.addAttribute("vehiculos", vehiculoRepository.findAll());
        return "reparacion_form";
    }

    @PostMapping
    public String createReparacion(@ModelAttribute("reparacion") Reparacion reparacion,
                                   @RequestParam("mecanicoId") String mecanicoId,
                                   @RequestParam("vehiculoId") String vehiculoId,
                                   @RequestParam("repuestoIds") List<String> repuestoIds) {
        Optional<User> mecanico = userRepository.findById(mecanicoId);
        Optional<Vehiculo> vehiculo = vehiculoRepository.findById(vehiculoId);
        List<Repuesto> repuestos = repuestoRepository.findAllById(repuestoIds);

        if (mecanico.isPresent() && vehiculo.isPresent() && !repuestos.isEmpty()) {
            reparacion.setMecanico(mecanico.get());
            reparacion.setVehiculo(vehiculo.get());
            reparacion.setRepuestos(repuestos);

            reparacionRepository.save(reparacion);
            return "redirect:/reparaciones";
        } else {
            // Manejar error (por ejemplo, devolver un mensaje de error o una vista de error)
            return "error";
        }
    }

    @PostMapping("/mecanico")
    public String createReparacionMecanico(@ModelAttribute("reparacion") Reparacion reparacion,
                                           @RequestParam("mecanicoId") String mecanicoId,
                                           @RequestParam("vehiculoId") String vehiculoId,
                                           @RequestParam("repuestoIds") List<String> repuestoIds) {
        Optional<User> mecanico = userRepository.findById(mecanicoId);
        Optional<Vehiculo> vehiculo = vehiculoRepository.findById(vehiculoId);
        List<Repuesto> repuestos = repuestoRepository.findAllById(repuestoIds);

        if (mecanico.isPresent() && vehiculo.isPresent() && !repuestos.isEmpty()) {
            reparacion.setMecanico(mecanico.get());
            reparacion.setVehiculo(vehiculo.get());
            reparacion.setRepuestos(repuestos);

            reparacionRepository.save(reparacion);
            return "redirect:/reparaciones/mecanico/"+reparacion.getMecanico().getId();
        } else {
            // Manejar error (por ejemplo, devolver un mensaje de error o una vista de error)
            return "error";
        }
    }


    @GetMapping("/edit/{id}")
    public String editReparacionForm(@PathVariable String id, Model model) {
        Optional<Reparacion> reparacion = reparacionRepository.findById(id);
        if (reparacion.isPresent()) {
            model.addAttribute("reparacion", reparacion.get());
            model.addAttribute("mecanicos", userRepository.findByRol(Rol.MECANICO));
            model.addAttribute("repuestos", repuestoRepository.findAll());
            model.addAttribute("vehiculos", vehiculoRepository.findAll());
            return "reparacion_form";
        } else {
            return "redirect:/reparaciones";
        }
    }

    @GetMapping("/edit/mecanico/{id}")
    public String editReparacionFormMecanico(@PathVariable String id, Model model) {
        Optional<Reparacion> reparacion = reparacionRepository.findById(id);
        if (reparacion.isPresent()) {
            model.addAttribute("reparacion", reparacion.get());
            model.addAttribute("mecanicos", reparacion.get().getMecanico());
            model.addAttribute("repuestos", repuestoRepository.findAll());
            model.addAttribute("vehiculos", vehiculoRepository.findAll());
            return "reparacion_form_mecanico";
        } else {
            return "redirect:/reparaciones/mecanico"+reparacion.get().getMecanico().getId();
        }
    }


    @PostMapping("/update/{id}")
    public String updateReparacion(@PathVariable String id, @ModelAttribute("reparacion") Reparacion reparacionDetails,
                                   @RequestParam("mecanicoId") String mecanicoId, @RequestParam("vehiculoId") String vehiculoId,
                                   @RequestParam("repuestoIds") List<String> repuestoIds) {
        Optional<Reparacion> reparacionOptional = reparacionRepository.findById(id);
        if (reparacionOptional.isPresent()) {
            Reparacion reparacionToUpdate = reparacionOptional.get();
            reparacionToUpdate.setFecha(reparacionDetails.getFecha());
            reparacionToUpdate.setNombreCliente(reparacionDetails.getNombreCliente());
            reparacionToUpdate.setDescripcion(reparacionDetails.getDescripcion());
            reparacionToUpdate.setEstado(reparacionDetails.getEstado());

            Optional<User> mecanicoOptional = userRepository.findById(mecanicoId);
            Optional<Vehiculo> vehiculoOptional = vehiculoRepository.findById(vehiculoId);
            List<Repuesto> repuestos = repuestoRepository.findAllById(repuestoIds);

            mecanicoOptional.ifPresent(reparacionToUpdate::setMecanico);
            vehiculoOptional.ifPresent(reparacionToUpdate::setVehiculo);
            reparacionToUpdate.setRepuestos(repuestos);

            reparacionRepository.save(reparacionToUpdate);
        }
        return "redirect:/reparaciones";
    }

    @PostMapping("/update/mecanico/{id}")
    public String updateReparacionMecanico(@PathVariable String id, @ModelAttribute("reparacion") Reparacion reparacionDetails,
                                           @RequestParam("mecanicoId") String mecanicoId, @RequestParam("vehiculoId") String vehiculoId,
                                           @RequestParam("repuestoIds") List<String> repuestoIds) {
        Optional<Reparacion> reparacionOptional = reparacionRepository.findById(id);
        if (reparacionOptional.isPresent()) {
            Reparacion reparacionToUpdate = reparacionOptional.get();
            reparacionToUpdate.setFecha(reparacionDetails.getFecha());
            reparacionToUpdate.setNombreCliente(reparacionDetails.getNombreCliente());
            reparacionToUpdate.setDescripcion(reparacionDetails.getDescripcion());
            reparacionToUpdate.setEstado(reparacionDetails.getEstado());

            Optional<User> mecanicoOptional = userRepository.findById(mecanicoId);
            Optional<Vehiculo> vehiculoOptional = vehiculoRepository.findById(vehiculoId);
            List<Repuesto> repuestos = repuestoRepository.findAllById(repuestoIds);

            mecanicoOptional.ifPresent(reparacionToUpdate::setMecanico);
            vehiculoOptional.ifPresent(reparacionToUpdate::setVehiculo);
            reparacionToUpdate.setRepuestos(repuestos);

            reparacionRepository.save(reparacionToUpdate);
        }
        return "redirect:/reparaciones/mecanico/"+reparacionOptional.get().getMecanico().getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteReparacion(@PathVariable String id) {
        Optional<Reparacion> reparacion = reparacionRepository.findById(id);
        if (reparacion.isPresent()) {
            reparacionRepository.delete(reparacion.get());
        }
        return "redirect:/reparaciones";
    }

    @GetMapping("/delete/mecanico/{id}")
    public String deleteReparacionMecanico(@PathVariable String id) {
        Optional<Reparacion> reparacion = reparacionRepository.findById(id);
        if (reparacion.isPresent()) {
            reparacionRepository.delete(reparacion.get());
        }
        return "redirect:/reparaciones/mecanico/"+reparacion.get().getMecanico().getId();
    }
}




















