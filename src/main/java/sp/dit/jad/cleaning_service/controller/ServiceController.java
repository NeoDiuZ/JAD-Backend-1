package sp.dit.jad.cleaning_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sp.dit.jad.cleaning_service.dto.ServiceDTO;
import sp.dit.jad.cleaning_service.service.ServiceService;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    // 1. Get all services (Return DTOs instead of entities)
    @GetMapping
    public ResponseEntity<List<ServiceDTO>> getAllServices() {
        return ResponseEntity.ok(serviceService.getAllServices());
    }

    // 2. Get a service by its ID
    @GetMapping("/{id}")
    public ResponseEntity<ServiceDTO> getServiceById(@PathVariable Integer id) {
        ServiceDTO service = serviceService.getServiceById(id);
        return service != null ? ResponseEntity.ok(service) : ResponseEntity.notFound().build();
    }

    // 3. Get services by category ID (Return DTOs)
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ServiceDTO>> getServicesByCategory(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(serviceService.getServicesByCategory(categoryId));
    }
}
