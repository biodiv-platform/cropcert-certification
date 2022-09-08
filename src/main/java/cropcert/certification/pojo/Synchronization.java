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
@Table(name = "synchronization")
@JsonIgnoreProperties
@ApiModel("Synchronization")
public class Synchronization implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6543986103157178072L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sync_id_generator")
	@SequenceGenerator(name = "sync_id_generator", sequenceName = "sync_id_seq", allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "farmer_id")
	private Long farmerId;

	@Column(name = "report_id")
	private Long reportId;

	@Column(name = "version")
	private Integer version;

	@Column(name = "sub_version")
	private Integer subVersion;

	@Column(name = "is_report_finalized")
	private Boolean isReportFinalized;

	@Column(name = "last_updated")
	private Timestamp lastUpdated;

	@Column(name = "updated_by")
	private Long updatedBy;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

	public Synchronization() {
		super();
	}

	public Synchronization(Long id, Long farmerId, Long reportId, Integer version, Integer subVersion,
			Boolean isReportFinalized, Timestamp lastUpdated, Long updatedBy, Boolean isDeleted) {
		super();
		this.id = id;
		this.farmerId = farmerId;
		this.reportId = reportId;
		this.version = version;
		this.subVersion = subVersion;
		this.isReportFinalized = isReportFinalized;
		this.lastUpdated = lastUpdated;
		this.updatedBy = updatedBy;
		this.isDeleted = isDeleted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFarmerId() {
		return farmerId;
	}

	public void setFarmerId(Long farmerId) {
		this.farmerId = farmerId;
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getSubVersion() {
		return subVersion;
	}

	public void setSubVersion(Integer subVersion) {
		this.subVersion = subVersion;
	}

	public Boolean getIsReportFinalized() {
		return isReportFinalized;
	}

	public void setIsReportFinalized(Boolean isReportFinalized) {
		this.isReportFinalized = isReportFinalized;
	}

	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
