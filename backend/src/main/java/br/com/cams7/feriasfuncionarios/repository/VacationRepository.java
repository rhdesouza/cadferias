/**
 * 
 */
package br.com.cams7.feriasfuncionarios.repository;

import static br.com.cams7.feriasfuncionarios.model.VacationEntity.WITH_CREATEDBY_LASTMODIFIEDBY_EMPLOYEE;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.cams7.feriasfuncionarios.model.VacationEntity;
import br.com.cams7.feriasfuncionarios.repository.common.SoftDeleteCrudRepository;

/**
 * @author ceanm
 *
 */
@Transactional
public interface VacationRepository extends VacationRepositoryCustom, SoftDeleteCrudRepository<VacationEntity, Long> {

	@Override
	@Transactional(readOnly = true)
	@EntityGraph(value = WITH_CREATEDBY_LASTMODIFIEDBY_EMPLOYEE)
	@Query("SELECT v FROM VacationEntity v WHERE v.id = :id AND v.active = true")
	Optional<VacationEntity> findById(@Param("id") Long id);

	@Transactional(readOnly = true)
	@Query("SELECT v.id FROM VacationEntity v WHERE v.employee.id = :employeeId AND v.active = true")
	Long[] findIdsByEmployeeId(@Param("employeeId") Long employeeId);
	
	@Query("SELECT v.employee.id FROM VacationEntity v WHERE v.id = :id AND v.active = true")
	Long findEmployeeIdById(@Param("id") Long id);

}