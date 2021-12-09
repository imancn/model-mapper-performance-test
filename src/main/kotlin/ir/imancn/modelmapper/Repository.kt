package ir.imancn.modelmapper

import ir.imancn.modelmapper.model.Entity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface Repository : JpaRepository<Entity, String>