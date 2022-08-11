package cropcert.certification.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;

@Entity
@Table(name = "advice")
@JsonIgnoreProperties
@ApiModel("Advice")
public class Advice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4809225755417237598L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "advice_id_generator")
	@SequenceGenerator(name = "advice_id_generator", sequenceName = "advice_id_seq", allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "advice")
	private String advice;
	@Column(name = "time")
	private Timestamp time;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "inspection_id")
	private Inspection inspection;

	public Advice() {
		super();
	}

	public Advice(Long id, String advice, Timestamp time, Inspection inspection) {
		super();
		this.id = id;
		this.advice = advice;
		this.time = time;
		this.inspection = inspection;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Inspection getInspection() {
		return inspection;
	}

	public void setInspection(Inspection inspection) {
		this.inspection = inspection;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
