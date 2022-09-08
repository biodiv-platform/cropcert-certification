package cropcert.certification.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;

@Entity
@Table(name = "signature")
@JsonIgnoreProperties
@ApiModel("Signature")
public class Signature implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4809225755417237598L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "signature_id_generator")
	@SequenceGenerator(name = "signature_id_generator", sequenceName = "signature_id_seq", allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "name")
	private String name;
	@Column(name = "date")
	private Timestamp date;
	@Column(name = "done")
	private Boolean done;
	@Column(name = "path")
	private String path;

	public Signature() {
		super();
	}

	public Signature(Long id, String name, Timestamp date, Boolean done, String path) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.done = done;
		this.path = path;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
