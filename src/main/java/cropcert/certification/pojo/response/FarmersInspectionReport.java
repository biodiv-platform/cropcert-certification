package cropcert.certification.pojo.response;

import java.sql.Timestamp;

import cropcert.certification.pojo.Inspection;
import cropcert.user.model.Farmer;

public class FarmersInspectionReport {

	private Long id;
	private String userName;
	private String firstName;
	private String lastName;
	private Timestamp dateOfBirth;
	private String gender;
	private String cellNumber;
	private String email;
	private String village;
	private String subCountry;
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

	private Inspection inspection;

	public FarmersInspectionReport() {
		super();
	}

	public FarmersInspectionReport(Farmer farmer, Integer version, Integer subVersion, Inspection inspection) {
		this.id = farmer.getId();
		this.userName = farmer.getUserName();
		this.firstName = farmer.getFirstName();
		this.lastName = farmer.getLastName();
		this.dateOfBirth = null;
		if (farmer.getDateOfBirth() != null)
			this.dateOfBirth = new Timestamp(farmer.getDateOfBirth().getTime());
		this.gender = farmer.getGender();
		this.cellNumber = farmer.getCellNumber();
		this.email = farmer.getEmail();
		this.village = farmer.getVillage();
		this.subCountry = farmer.getSubCountry();
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

		this.inspection = inspection;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Timestamp getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Timestamp dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCellNumber() {
		return cellNumber;
	}

	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getSubCountry() {
		return subCountry;
	}

	public void setSubCountry(String subCountry) {
		this.subCountry = subCountry;
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

	public Inspection getInspection() {
		return inspection;
	}

	public void setInspection(Inspection inspection) {
		this.inspection = inspection;
	}
}
