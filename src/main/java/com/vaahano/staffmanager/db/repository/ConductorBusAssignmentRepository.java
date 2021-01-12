package com.vaahano.staffmanager.db.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vaahano.staffmanager.db.domain.BusAssignment;

@Repository
public interface ConductorBusAssignmentRepository extends MongoRepository<BusAssignment,String> {

}
