/**
 * 
 */
package br.com.cams7.cadferias.repository;

import static br.com.cams7.cadferias.model.VacationEntity.WITH_CREATEDBY_LASTMODIFIEDBY_EMPLOYEE;
import static br.com.cams7.cadferias.model.VacationEntity.WITH_EMPLOYEE;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.cams7.cadferias.model.VacationEntity;
import br.com.cams7.cadferias.repository.common.SoftDeleteCrudRepository;

/**
 * @author ceanm
 *
 */
public interface VacationRepository extends VacationRepositoryCustom, SoftDeleteCrudRepository<VacationEntity, Long> {

	@Override
	@EntityGraph(value = WITH_EMPLOYEE)
	@Query("SELECT v FROM VacationEntity v WHERE v.entityId = :id AND v.active = true")
	Optional<VacationEntity> findById(@Param("id") Long id);
	
	@Override
	@EntityGraph(value = WITH_CREATEDBY_LASTMODIFIEDBY_EMPLOYEE)
	@Query("SELECT v FROM VacationEntity v WHERE v.entityId = :id AND v.active = true")
	Optional<VacationEntity> findWithAuditById(@Param("id") Long id);

	@Query("SELECT v.entityId FROM VacationEntity v WHERE v.employee.entityId = :employeeId AND v.active = true")
	Long[] findIdsByEmployeeId(@Param("employeeId") Long employeeId);
	
	@Query("SELECT v.employee.entityId FROM VacationEntity v WHERE v.entityId = :id AND v.active = true")
	Long findEmployeeIdById(@Param("id") Long id);

}
