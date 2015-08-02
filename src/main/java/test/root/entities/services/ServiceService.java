package test.root.entities.services;

import org.springframework.beans.factory.annotation.Autowired;
import test.root.entities.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Filip on 2015-08-01.
 */
@org.springframework.stereotype.Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public Collection<Service> getAllService() {
        //TODO encapsulate
        Collection<Service> list = new ArrayList<Service>();
        for (Service item : serviceRepository.findAll()) {
            list.add(item);
        }
        return list;
    }


    public Service getServiceById(long id) {
        return serviceRepository.findOne(id);
    }
    public Service getServiceByBookId(long id) {
        return serviceRepository.findOneByBookId(id);
    }
    public List<Service> getServiceByUserId(long id) {
        return serviceRepository.findByUserId(id);
    }

    public Service create(long startTime, long endTime, User user, Book book) {
        return serviceRepository.save(new Service(startTime, endTime, user, book));
    }

    public void returnBook(Book book){
        delete(serviceRepository.findOneByBookId(book.getId()));
    }

    public void delete(Service service){
        serviceRepository.delete(service);
    }
}
