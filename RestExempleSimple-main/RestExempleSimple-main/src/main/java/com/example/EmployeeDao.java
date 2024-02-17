package com.example;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import connectionDB.ConnectionDb;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/employees")
public class EmployeeDao {

    private final SessionFactory sessionFactory = ConnectionDb.getFactory();

    // CRUD operations
    // Create (POST)
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/add")
    @Transactional
    public void createEmployee(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(employee);
            session.getTransaction().commit();
        }
    }

    // Read (GET)
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee getEmployeeById(@PathParam("id") Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Employee.class, id);
        }
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)  
    public List<Employee> getAllEmployees() {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<Employee> query = session.createQuery("SELECT e FROM Employee e", Employee.class);
            return query.getResultList();
        }
    }

    // Update (PUT)
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public void updateEmployee(@PathParam("id") Long id, Employee updatedEmployee) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Employee existingEmployee = session.find(Employee.class, id);
            if (existingEmployee != null) {
                // Update fields
                existingEmployee.setNom(updatedEmployee.getNom());
                existingEmployee.setPrenom(updatedEmployee.getPrenom());
                existingEmployee.setDateNaissance(updatedEmployee.getDateNaissance());
                existingEmployee.setSalaire(updatedEmployee.getSalaire());
                // Update other fields as needed
            }
            session.getTransaction().commit();
        }
    }

    // Delete (DELETE)
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public void deleteEmployee(@PathParam("id") Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Employee employee = session.find(Employee.class, id);
            if (employee != null) {
                session.remove(employee);
            }
            session.getTransaction().commit();
        }
    }
}
