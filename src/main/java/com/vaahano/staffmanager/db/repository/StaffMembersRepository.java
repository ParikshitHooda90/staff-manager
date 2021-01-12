package com.vaahano.staffmanager.db.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vaahano.staffmanager.db.domain.StaffMember;

@Repository
public interface StaffMembersRepository extends MongoRepository<StaffMember, String> {

}
