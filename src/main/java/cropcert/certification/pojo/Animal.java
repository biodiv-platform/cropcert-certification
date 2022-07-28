package cropcert.certification.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cropcert.certification.pojo.enumtype.HusbandaryType;
import io.swagger.annotations.ApiModel;

@Entity
@Table(name = "animal")
@JsonIgnoreProperties
@ApiModel("Animal")
public class Animal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7761367629628125279L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "animal_id_generator")
	@SequenceGenerator(name = "animal_id_generator", sequenceName = "animal_id_seq", allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "type")
	private String type;
	@Column(name = "number")
	private Long number;
	@Column(name = "husbandryType")
	private HusbandaryType husbandryType;
	@Column(name = "medication")
	private Boolean medication;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "inspection_id")
	private Inspection inspection;

	public Animal() {
		super();
	}

	public Animal(Long id, String type, Long number, HusbandaryType husbandryType, Boolean medication,
			Inspection inspection) {
		super();
		this.id = id;
		this.type = type;
		this.number = number;
		this.husbandryType = husbandryType;
		this.medication = medication;
		this.inspection = inspection;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public HusbandaryType getHusbandryType() {
		return husbandryType;
	}

	public void setHusbandryType(HusbandaryType husbandryType) {
		this.husbandryType = husbandryType;
	}

	public Boolean getMedication() {
		return medication;
	}

	public void setMedication(Boolean medication) {
		this.medication = medication;
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
