/**
 * 
 */
package br.com.cams7.feriasfuncionarios.model;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.cams7.feriasfuncionarios.common.Views;
import br.com.cams7.feriasfuncionarios.model.common.Auditable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ceanm
 *
 */
@ApiModel(description = "Entidade que representa a férias do funcionário.")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id", callSuper = false)
@Entity
@Table(name = "TB_FERIAS")
public class VacationEntity extends Auditable<Long> {

	@ApiModelProperty(notes = "Identificador único da férias.", example = "1", required = true, position = 5)
	@JsonView(Views.Public.class)
	@Id
	@SequenceGenerator(name = "SQ_FERIAS", sequenceName = "SQ_FERIAS", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "SQ_FERIAS")
	@Column(name = "ID_FERIAS", nullable = false)
	private Long id;

	@ApiModelProperty(notes = "Funcionário do qual pertence a férias.", required = true, position = 6)
	@JsonView(Views.Public.class)
	@NotNull
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "ID_FUNCIONARIO")
	private EmployeeEntity employee;

	@ApiModelProperty(notes = "Data inicial da férias.", example = "05/06/2019", required = true, position = 7)
	@JsonView(Views.Public.class)
	@NotNull
	@Column(name = "DATA_INICIAL")
	private LocalDate startDate;

	@ApiModelProperty(notes = "Data final da férias.", example = "06/06/2019", required = true, position = 8)
	@JsonView(Views.Public.class)
	@NotNull
	@Column(name = "DATA_FINAL")
	private LocalDate endDate;
}
