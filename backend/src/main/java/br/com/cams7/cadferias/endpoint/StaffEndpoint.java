/**
 * 
 */
package br.com.cams7.cadferias.endpoint;

import static br.com.cams7.cadferias.endpoint.StaffEndpoint.STAFFENDPOINT_PATH;
import static br.com.cams7.cadferias.model.RoleEntity.ROLE_REGISTER_NEW_EMPLOYEE;
import static br.com.cams7.cadferias.model.RoleEntity.ROLE_UPDATE_EMPLOYEE_DATA;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.cams7.cadferias.common.Views.Public;
import br.com.cams7.cadferias.endpoint.common.BaseEndpoint;
import br.com.cams7.cadferias.model.StaffEntity;
import br.com.cams7.cadferias.model.vo.SearchBySelectVO;
import br.com.cams7.cadferias.model.vo.filter.StaffFilterVO;
import br.com.cams7.cadferias.service.StaffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author ceanm
 *
 */
@Api("Endpoint utilizado para criação, recuperação, atualização e exclusão de equipes.")
@RestController
@RequestMapping(path = STAFFENDPOINT_PATH, consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
public class StaffEndpoint extends BaseEndpoint<StaffService, StaffEntity, Long, StaffFilterVO> {

	public static final String STAFFENDPOINT_PATH = "/staffs";

	@ApiOperation("Carrega as equipes pelo filtro de busca do select.")
	@PreAuthorize("hasRole('" + ROLE_REGISTER_NEW_EMPLOYEE + "') or hasRole('" + ROLE_UPDATE_EMPLOYEE_DATA + "')")
	@JsonView(Public.class)
	@ResponseStatus(value = OK)
	@PostMapping(path = "searchByName")
	public Iterable<StaffEntity> getByName(
			@ApiParam("Filtro de busca informado.") @Valid @RequestBody SearchBySelectVO search) {
		return service.getByName(search);
	}
}
