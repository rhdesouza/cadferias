/**
 * 
 */
package br.com.cams7.cadferias.service.common;

import java.io.Serializable;

import br.com.cams7.cadferias.model.common.Auditable;
import br.com.cams7.cadferias.model.vo.SearchVO;
import br.com.cams7.cadferias.model.vo.filter.AuditableFilterVO;
import br.com.cams7.cadferias.model.vo.pagination.PageVO;

/**
 * @author ceanm
 *
 */
public interface BaseService<E extends Auditable<ID>, ID extends Serializable, F extends AuditableFilterVO> {

//	Iterable<E> getAll();

	E getById(ID id);
	
	E getWithAuditById(ID id);

	boolean existsById(ID id);

	E create(E entity);

	E update(E entity);

	void delete(ID id);

	void deleteAllById(Iterable<ID> ids);

	PageVO<E, ID> getBySearch(SearchVO<F> search);
}
