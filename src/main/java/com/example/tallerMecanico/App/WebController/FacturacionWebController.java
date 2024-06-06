package com.example.tallerMecanico.App.WebController;

import com.example.tallerMecanico.App.Entity.Facturacion;
import com.example.tallerMecanico.App.Entity.Reparacion;
import com.example.tallerMecanico.App.Entity.Repuesto;
import com.example.tallerMecanico.App.Repository.FacturacionRepository;
import com.example.tallerMecanico.App.Repository.ReparacionRepository;
import com.example.tallerMecanico.App.Repository.RepuestoRepository;
import com.example.tallerMecanico.App.Service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/facturacion")
public class FacturacionWebController {
    @Autowired
    private FacturacionRepository facturacionRepository;

    @Autowired
    private ReparacionRepository reparacionRepository;

    @Autowired
    private RepuestoRepository repuestoRepository;

    @Autowired
    private PdfService pdfService;

    @GetMapping("/all")
    public String getAllFacturas(Model model) {
        List<Facturacion> facturas = facturacionRepository.findAll();
        model.addAttribute("facturas", facturas);
        return "facturas";
    }

    @GetMapping("/create")
    public String showFacturaForm(Model model) {
        List<Reparacion> reparaciones = reparacionRepository.findAll();
        List<Repuesto> repuestos = repuestoRepository.findAll();
        model.addAttribute("factura", new Facturacion());
        model.addAttribute("reparaciones", reparaciones);
        model.addAttribute("repuestos", repuestos);
        return "factura-form";
    }

    @PostMapping("/create")
    public String createFactura(@ModelAttribute("factura") Facturacion factura) {
        Optional<Reparacion> reparacion = reparacionRepository.findById(factura.getIdReparacion());
        double total = factura.calcularTotal(reparacion.get().getRepuestos());
        factura.setRepuestos(reparacion.get().getRepuestos());
        factura.setTotal(total);
        facturacionRepository.save(factura);
        return "redirect:/facturacion/all";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        Facturacion factura = facturacionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid factura Id:" + id));
        List<Reparacion> reparaciones = reparacionRepository.findAll();
        List<Repuesto> repuestos = repuestoRepository.findAll();
        model.addAttribute("factura", factura);
        model.addAttribute("reparaciones", reparaciones);
        model.addAttribute("repuestos", repuestos);
        return "update-factura";
    }

    @PostMapping("/update/{id}")
    public String updateFactura(@PathVariable("id") String id, @ModelAttribute("factura") Facturacion factura) {
        Optional<Reparacion> reparacion = reparacionRepository.findById(factura.getIdReparacion());
        double total = factura.calcularTotal(reparacion.get().getRepuestos());
        factura.setRepuestos(reparacion.get().getRepuestos());
        factura.setTotal(total);
        facturacionRepository.save(factura);
        return "redirect:/facturacion/all";
    }

    @PostMapping("/delete/{id}")
    public String deleteFactura(@PathVariable("id") String id) {
        facturacionRepository.deleteById(id);
        return "redirect:/facturacion/all";
    }

    @GetMapping("/pdf/{id}")
    public ResponseEntity<InputStreamResource> generatePdf(@PathVariable("id") String id) {
        Facturacion factura = facturacionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid factura Id:" + id));

        ByteArrayInputStream bis = pdfService.generateInvoicePdf(factura);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=factura.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

}
