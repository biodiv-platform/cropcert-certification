package cropcert.certification.pojo.response;

import cropcert.certification.pojo.Inspection;
import cropcert.entities.model.UserFarmerDetail;

public class FarmersInspectionReport {

	private Long id;
	private String membershipId;
	private Integer numCoffeePlots;
	private Integer numCoffeeTrees;
	private Float farmArea;
	private Float coffeeArea;
	private String farmerCode;
	private Long ccCode;
	private String ccName;
	private String coName;
	private String unionName;
	private Long fieldCoOrdinator;
	private Integer version;
	private Integer subVersion;

	private String inspectorName;

	private Inspection inspection;

	public FarmersInspectionReport() {
		super();
	}

	public FarmersInspectionReport(UserFarmerDetail farmer, Integer version, Integer subVersion, String inspectorName,
			Inspection inspection) {
		this.id = farmer.getUserId();
		this.membershipId = farmer.getMembershipId();
		this.numCoffeePlots = farmer.getNumCoffeePlots();
		this.numCoffeeTrees = farmer.getNumCoffeeTrees();
		this.farmArea = farmer.getFarmArea();
		this.coffeeArea = farmer.getCoffeeArea();
		this.farmerCode = farmer.getFarmerCode();
		this.ccCode = farmer.getCcCode();
		this.ccName = farmer.getCcName();
		this.coName = farmer.getCoName();
		this.unionName = farmer.getUnionName();
		this.fieldCoOrdinator = farmer.getFieldCoOrdinator();

		this.version = version;
		this.subVersion = subVersion;

		this.inspectorName = inspectorName;

		this.inspection = inspection;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMembershipId() {
		return membershipId;
	}

	public void setMembershipId(String membershipId) {
		this.membershipId = membershipId;
	}

	public Integer getNumCoffeePlots() {
		return numCoffeePlots;
	}

	public void setNumCoffeePlots(Integer numCoffeePlots) {
		this.numCoffeePlots = numCoffeePlots;
	}

	public Integer getNumCoffeeTrees() {
		return numCoffeeTrees;
	}

	public void setNumCoffeeTrees(Integer numCoffeeTrees) {
		this.numCoffeeTrees = numCoffeeTrees;
	}

	public Float getFarmArea() {
		return farmArea;
	}

	public void setFarmArea(Float farmArea) {
		this.farmArea = farmArea;
	}

	public Float getCoffeeArea() {
		return coffeeArea;
	}

	public void setCoffeeArea(Float coffeeArea) {
		this.coffeeArea = coffeeArea;
	}

	public String getFarmerCode() {
		return farmerCode;
	}

	public void setFarmerCode(String farmerCode) {
		this.farmerCode = farmerCode;
	}

	public Long getCcCode() {
		return ccCode;
	}

	public void setCcCode(Long ccCode) {
		this.ccCode = ccCode;
	}

	public String getCcName() {
		return ccName;
	}

	public void setCcName(String ccName) {
		this.ccName = ccName;
	}

	public String getCoName() {
		return coName;
	}

	public void setCoName(String coName) {
		this.coName = coName;
	}

	public String getUnionName() {
		return unionName;
	}

	public void setUnionName(String unionName) {
		this.unionName = unionName;
	}

	public Long getFieldCoOrdinator() {
		return fieldCoOrdinator;
	}

	public void setFieldCoOrdinator(Long fieldCoOrdinator) {
		this.fieldCoOrdinator = fieldCoOrdinator;
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

	public String getInspectorName() {
		return inspectorName;
	}

	public void setInspectorName(String inspectorName) {
		this.inspectorName = inspectorName;
	}

	public Inspection getInspection() {
		return inspection;
	}

	public void setInspection(Inspection inspection) {
		this.inspection = inspection;
	}
}
