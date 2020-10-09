package cropcert.certification.pojo.response;

import java.sql.Timestamp;

import cropcert.certification.pojo.Synchronization;
import cropcert.user.model.Farmer;

public class ICSFarmerList {

	private Long farmerId;
	private String farmerFirstName;
	private String farmerLastName;

	private Long collectionCenterId;
	private String collectionCenterName;
	private String cooperativeName;

	private Long reportId;
	private Long prevReportId;
	private Long lastApprovedReportId;
	private Integer version;
	private Integer subVersion;
	private Boolean isReportFinalized;
	private Timestamp lastUpdated;
	
	public ICSFarmerList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ICSFarmerList(Farmer farmer, Synchronization synchronization) {
		super();
		this.farmerId = farmer.getId();
		this.farmerFirstName = farmer.getFirstName();
		this.farmerLastName = farmer.getLastName();
		this.cooperativeName = farmer.getCoName();
		this.collectionCenterId = farmer.getCcCode();
		this.collectionCenterName = farmer.getCcName();

		this.reportId = synchronization.getReportId();
		this.version = synchronization.getVersion();
		this.subVersion = synchronization.getSubVersion();
		this.isReportFinalized = synchronization.getIsReportFinalized();
		this.lastUpdated = synchronization.getLastUpdated();
	}

	public ICSFarmerList(Long farmerId, String farmerFirstName, String farmerLastName, Long collectionCenterId,
			String collectionCenterName, String cooperativeName, Long reportId, Integer version, Integer subVersion,
			Boolean isReportFinalized, Timestamp lastUpdated) {
		super();
		this.farmerId = farmerId;
		this.farmerFirstName = farmerFirstName;
		this.farmerLastName = farmerLastName;
		this.collectionCenterId = collectionCenterId;
		this.collectionCenterName = collectionCenterName;
		this.cooperativeName = cooperativeName;
		this.reportId = reportId;
		this.version = version;
		this.subVersion = subVersion;
		this.isReportFinalized = isReportFinalized;
		this.lastUpdated = lastUpdated;
	}

	public Long getFarmerId() {
		return farmerId;
	}

	public void setFarmerId(Long farmerId) {
		this.farmerId = farmerId;
	}

	public String getFarmerFirstName() {
		return farmerFirstName;
	}

	public void setFarmerFirstName(String farmerFirstName) {
		this.farmerFirstName = farmerFirstName;
	}

	public String getFarmerLastName() {
		return farmerLastName;
	}

	public void setFarmerLastName(String farmerLastName) {
		this.farmerLastName = farmerLastName;
	}

	public Long getCollectionCenterId() {
		return collectionCenterId;
	}

	public void setCollectionCenterId(Long collectionCenterId) {
		this.collectionCenterId = collectionCenterId;
	}

	public String getCollectionCenterName() {
		return collectionCenterName;
	}

	public void setCollectionCenterName(String collectionCenterName) {
		this.collectionCenterName = collectionCenterName;
	}

	public String getCooperativeName() {
		return cooperativeName;
	}

	public void setCooperativeName(String cooperativeName) {
		this.cooperativeName = cooperativeName;
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}
	
	public Long getPrevReportId() {
		return prevReportId;
	}
	
	public void setPrevReportId(Long prevReportId) {
		this.prevReportId = prevReportId;
	}
	
	public Long getLastApprovedReportId() {
		return lastApprovedReportId;
	}
	
	public void setLastApprovedReportId(Long lastApprovedReportId) {
		this.lastApprovedReportId = lastApprovedReportId;
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
}
