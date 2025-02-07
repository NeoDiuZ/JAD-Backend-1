package sp.dit.jad.cleaning_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import sp.dit.jad.cleaning_service.dto.ServiceDTO;
import sp.dit.jad.cleaning_service.model.ServiceEntity;
import sp.dit.jad.cleaning_service.repository.ServiceRepository;

@Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<ServiceDTO> getAllServices() {
        return serviceRepository.findAllWithCategory().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ServiceDTO getServiceById(Integer id) {
        return serviceRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    public List<ServiceDTO> getServicesByCategory(Integer categoryId) {
        return serviceRepository.findByCategoryId(categoryId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ServiceDTO convertToDTO(ServiceEntity service) {
        return new ServiceDTO(
                service.getId(),
                service.getName(),
                service.getDescription(),
                service.getPrice(),
                service.getImagePath(),
                service.getCategory().getName()
        );
    }
}
