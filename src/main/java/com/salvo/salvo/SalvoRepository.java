package com.salvo.salvo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Map;

@RepositoryRestResource
//public interface SalvoRepository extends JpaRepository<Salvo, Long>, List<Map<String, Object>> {
//}

public interface SalvoRepository extends JpaRepository<Salvo, Long> {
}