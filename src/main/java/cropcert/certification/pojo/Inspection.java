package cropcert.certification.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cropcert.certification.pojo.enumtype.CertificationStatus;
import cropcert.certification.pojo.enumtype.CertificationVersion;
import cropcert.certification.pojo.enumtype.Decision;
import cropcert.certification.pojo.enumtype.Quantity;
import io.swagger.annotations.ApiModel;

@Entity
@Table(name = "inspection")
@JsonIgnoreProperties
@ApiModel("Inspection")
public class Inspection implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5497916199514044930L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inspection_id_generator")
	@SequenceGenerator(name = "inspection_id_generator", sequenceName = "inspection_id_seq", allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "inspector_id", nullable = false)
	private Long inspectorId;

	@Column(name = "farmer_id")
	private Long farmerId;

	@Column(name = "date")
	private Timestamp date;

	// Verification
	@Column(name = "verification_date")
	private Timestamp verificationDate;
	@Column(name = "farmer_contract")
	private Timestamp farmerContract;

	// General information
	@Column(name = "last_used_chemicals")
	private Timestamp lastUsedChemicals;
	@Column(name = "chemicals_on_intercrop")
	private Boolean chemicalsOnIntercrop;
	@Column(name = "chemicals_on_non_coffee_field")
	private Boolean chemicalsOnNonCoffeeField;
	@Column(name = "cutivatation_not_conducted_within_5m_water_source")
	private Boolean cutivatationNotConductedWithin5mWaterSource;
	@Column(name = "manure_90_days_or_loss_before_harvest")
	private Boolean manure90DaysOrLossBeforeHarvest;
	@Column(name = "understanding_of_organic_ft_standards")
	private Boolean understandingOfOrganicFTStandards;
	@Column(name = "weed_control_adequate")
	private Boolean weedControlAdequate;
	@Column(name = "non_coffee_trees_planted")
	@Enumerated(EnumType.STRING)
	private Quantity nonCoffeeTreesPlanted;
	@Column(name = "signs_of_erosion")
	private Boolean signsOfErosion;
	@Column(name = "erosion_control_adequate")
	private Boolean erosionControlAdequate;
	@Column(name = "burning_of_crop_waste")
	private Boolean burningOfCropWaste;
	@Column(name = "farmer_hire_labour")
	private Boolean farmerHireLabour;
	@Column(name = "is_labour_fairly_treated")
	private Boolean isLabourFairlyTreated;
	@Column(name = "is_child_labour_imployed")
	private Boolean isChildLabourImployed;
	@Column(name = "plastic_disposal")
	private Quantity plasticDisposal;
	@Column(name = "is_other_waste_disposal_adequate")
	private Boolean isOtherWasteDisposalAdequate;
	@Column(name = "is_hh_making_joint_decision")
	private Boolean isHHMakingJointDecision;
	@Column(name = "is_hh_taking_farming_as_family_business")
	private Boolean isHHTakingFarmingAsFamilyBusiness;
	@Column(name = "comments")
	private String comments;

	// Farm list for the farmer
	@OneToMany(mappedBy = "inspection", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<FarmPlot> farms = new HashSet<>();

	// Summary column
	@Column(name = "number_of_coffee_fields")
	private Integer numberOfCoffeeFields;
	@Column(name = "area_under_coffee")
	private Double areaUnderCoffee;
	@Column(name = "productive_trees")
	private Long productiveTrees;
	@Column(name = "total_area_of_farm")
	private Double totalAreaOfFarm;

	@Column(name = "known_to_harvest_ripe_cherries")
	private Boolean knownToHarvestRipeCherries;
	@Column(name = "practices_post_harvest_handlling")
	private Boolean practicesPostHarvestHandlling;

	// Animals
	@Column(name = "has_live_stock")
	private Boolean hasLiveStock;
	@Column(name = "chemical_treatment_on_livestock")
	private Boolean chemicalTreatmentOnLivestock;
	@Column(name = "livestock_treatment_conducted_5m_from_coffee")
	private Boolean livestockTreatmentConducted5mFromCoffee;
	@OneToMany(mappedBy = "inspection", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Animal> animals = new HashSet<>();

	// Recommendation
	@Column(name = "has_farmer_implemented_previous_advice")
	@Enumerated(EnumType.STRING)
	private Decision hasFarmerImplementedPreviousAdvice;

	@OneToMany(mappedBy = "inspection", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Advice> advices = new HashSet<>();
	@Column(name = "made_serious_violation")
	private Boolean madeSeriousViolation;
	@Column(name = "violation_date")
	private Timestamp violationDate;
	@Column(name = "isRecommended_organic_certificatation")
	private Boolean isRecommendedOrganicCertificatation;

	@Column(name = "board_agm_minutes_kept")
	private Boolean boardAGMMinutesKept;
	@Column(name = "membership_lists_and_shares_updated")
	private Boolean membershipListsAndSharesUpdated;
	@Column(name = "is_annual_budget_and_audited_accounts")
	private Boolean isAnnualBudgetAndAuditedAccounts;
	@Column(name = "is_fair_trade_premium_budget_and_workplan")
	private Boolean isFairTradePremiumBudgetAndWorkplan;
	@Column(name = "is_envirnment_committe_and_its_workplan")
	private Boolean isEnvirnmentCommitteAndItsWorkplan;
	@Column(name = "is_ft_contract_person_appointed")
	private Boolean isFTContractPersonAppointed;

	@Column(name = "certification_status")
	private CertificationStatus certificationStatus;
	@Column(name = "certification_version")
	private CertificationVersion certificationVersion;

	@Column(name = "geo_location")
	private String geoLocation;

	@JoinColumn(name = "farmer")
	@OneToOne(cascade = CascadeType.ALL)
	private Signature farmer;
	@JoinColumn(name = "field_coordinator")
	@OneToOne(cascade = CascadeType.ALL)
	private Signature fieldCoordinator;
	@JoinColumn(name = "ics_manager")
	@OneToOne(cascade = CascadeType.ALL)
	private Signature icsManager;
	@JoinColumn(name = "chair_person")
	@OneToOne(cascade = CascadeType.ALL)
	private Signature chairPerson;

	public Inspection() {
		super();
	}

	public Inspection(Long id, Long inspectorId, Long farmerId, Timestamp date, Timestamp verificationDate,
			Timestamp farmerContract, Timestamp lastUsedChemicals, Boolean chemicalsOnIntercrop,
			Boolean chemicalsOnNonCoffeeField, Boolean manure90DaysOrLossBeforeHarvest,
			Boolean understandingOfOrganicFTStandards, Boolean weedControlAdequate, Quantity nonCoffeeTreesPlanted,
			Boolean signsOfErosion, Boolean erosionControlAdequate, Boolean burningOfCropWaste,
			Boolean farmerHireLabour, Boolean isLabourFairlyTreated, Boolean isChildLabourImployed,
			Quantity plasticDisposal, Boolean isOtherWasteDisposalAdequate, Boolean isHHMakingJointDecision,
			Boolean isHHTakingFarmingAsFamilyBusiness, String comments, Set<FarmPlot> farms,
			Integer numberOfCoffeeFields, Double areaUnderCoffee, Long productiveTrees, Double totalAreaOfFarm,
			Boolean knownToHarvestRipeCherries, Boolean practicesPostHarvestHandlling, Boolean hasLiveStock,
			Boolean chemicalTreatmentOnLivestock, Boolean livestockTreatmentConducted5mFromCoffee, Set<Animal> animals,
			Decision hasFarmerImplementedPreviousAdvice, Set<Advice> advices, Boolean madeSeriousViolation,
			Timestamp violationDate, Boolean isRecommendedOrganicCertificatation, Boolean boardAGMMinutesKept,
			Boolean membershipListsAndSharesUpdated, Boolean isAnnualBudgetAndAuditedAccounts,
			Boolean isFairTradePremiumBudgetAndWorkplan, Boolean isEnvirnmentCommitteAndItsWorkplan,
			Boolean isFTContractPersonAppointed, Signature farmer, Signature fieldCoordinator, Signature icsManager,
			Signature chairPerson) {
		super();
		this.id = id;
		this.inspectorId = inspectorId;
		this.farmerId = farmerId;
		this.date = date;
		this.verificationDate = verificationDate;
		this.farmerContract = farmerContract;
		this.lastUsedChemicals = lastUsedChemicals;
		this.chemicalsOnIntercrop = chemicalsOnIntercrop;
		this.chemicalsOnNonCoffeeField = chemicalsOnNonCoffeeField;
		this.manure90DaysOrLossBeforeHarvest = manure90DaysOrLossBeforeHarvest;
		this.understandingOfOrganicFTStandards = understandingOfOrganicFTStandards;
		this.weedControlAdequate = weedControlAdequate;
		this.nonCoffeeTreesPlanted = nonCoffeeTreesPlanted;
		this.signsOfErosion = signsOfErosion;
		this.erosionControlAdequate = erosionControlAdequate;
		this.burningOfCropWaste = burningOfCropWaste;
		this.farmerHireLabour = farmerHireLabour;
		this.isLabourFairlyTreated = isLabourFairlyTreated;
		this.isChildLabourImployed = isChildLabourImployed;
		this.plasticDisposal = plasticDisposal;
		this.isOtherWasteDisposalAdequate = isOtherWasteDisposalAdequate;
		this.isHHMakingJointDecision = isHHMakingJointDecision;
		this.isHHTakingFarmingAsFamilyBusiness = isHHTakingFarmingAsFamilyBusiness;
		this.comments = comments;
		this.farms = farms;
		this.numberOfCoffeeFields = numberOfCoffeeFields;
		this.areaUnderCoffee = areaUnderCoffee;
		this.productiveTrees = productiveTrees;
		this.totalAreaOfFarm = totalAreaOfFarm;
		this.knownToHarvestRipeCherries = knownToHarvestRipeCherries;
		this.practicesPostHarvestHandlling = practicesPostHarvestHandlling;
		this.hasLiveStock = hasLiveStock;
		this.chemicalTreatmentOnLivestock = chemicalTreatmentOnLivestock;
		this.livestockTreatmentConducted5mFromCoffee = livestockTreatmentConducted5mFromCoffee;
		this.animals = animals;
		this.hasFarmerImplementedPreviousAdvice = hasFarmerImplementedPreviousAdvice;
		this.advices = advices;
		this.madeSeriousViolation = madeSeriousViolation;
		this.violationDate = violationDate;
		this.isRecommendedOrganicCertificatation = isRecommendedOrganicCertificatation;
		this.boardAGMMinutesKept = boardAGMMinutesKept;
		this.membershipListsAndSharesUpdated = membershipListsAndSharesUpdated;
		this.isAnnualBudgetAndAuditedAccounts = isAnnualBudgetAndAuditedAccounts;
		this.isFairTradePremiumBudgetAndWorkplan = isFairTradePremiumBudgetAndWorkplan;
		this.isEnvirnmentCommitteAndItsWorkplan = isEnvirnmentCommitteAndItsWorkplan;
		this.isFTContractPersonAppointed = isFTContractPersonAppointed;
		this.farmer = farmer;
		this.fieldCoordinator = fieldCoordinator;
		this.icsManager = icsManager;
		this.chairPerson = chairPerson;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInspectorId() {
		return inspectorId;
	}

	public void setInspectorId(Long inspectorId) {
		this.inspectorId = inspectorId;
	}

	public Long getFarmerId() {
		return farmerId;
	}

	public void setFarmerId(Long farmerId) {
		this.farmerId = farmerId;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Timestamp getVerificationDate() {
		return verificationDate;
	}

	public void setVerificationDate(Timestamp verificationDate) {
		this.verificationDate = verificationDate;
	}

	public Timestamp getFarmerContract() {
		return farmerContract;
	}

	public void setFarmerContract(Timestamp farmerContract) {
		this.farmerContract = farmerContract;
	}

	public Timestamp getLastUsedChemicals() {
		return lastUsedChemicals;
	}

	public void setLastUsedChemicals(Timestamp lastUsedChemicals) {
		this.lastUsedChemicals = lastUsedChemicals;
	}

	public Boolean getChemicalsOnIntercrop() {
		return chemicalsOnIntercrop;
	}

	public void setChemicalsOnIntercrop(Boolean chemicalsOnIntercrop) {
		this.chemicalsOnIntercrop = chemicalsOnIntercrop;
	}

	public Boolean getChemicalsOnNonCoffeeField() {
		return chemicalsOnNonCoffeeField;
	}

	public void setChemicalsOnNonCoffeeField(Boolean chemicalsOnNonCoffeeField) {
		this.chemicalsOnNonCoffeeField = chemicalsOnNonCoffeeField;
	}

	public Boolean getCutivatationNotConductedWithin5mWaterSource() {
		return cutivatationNotConductedWithin5mWaterSource;
	}

	public void setCutivatationNotConductedWithin5mWaterSource(Boolean cutivatationNotConductedWithin5mWaterSource) {
		this.cutivatationNotConductedWithin5mWaterSource = cutivatationNotConductedWithin5mWaterSource;
	}

	public Boolean getManure90DaysOrLossBeforeHarvest() {
		return manure90DaysOrLossBeforeHarvest;
	}

	public void setManure90DaysOrLossBeforeHarvest(Boolean manure90DaysOrLossBeforeHarvest) {
		this.manure90DaysOrLossBeforeHarvest = manure90DaysOrLossBeforeHarvest;
	}

	public Boolean getUnderstandingOfOrganicFTStandards() {
		return understandingOfOrganicFTStandards;
	}

	public void setUnderstandingOfOrganicFTStandards(Boolean understandingOfOrganicFTStandards) {
		this.understandingOfOrganicFTStandards = understandingOfOrganicFTStandards;
	}

	public Boolean getWeedControlAdequate() {
		return weedControlAdequate;
	}

	public void setWeedControlAdequate(Boolean weedControlAdequate) {
		this.weedControlAdequate = weedControlAdequate;
	}

	public Quantity getNonCoffeeTreesPlanted() {
		return nonCoffeeTreesPlanted;
	}

	public void setNonCoffeeTreesPlanted(Quantity nonCoffeeTreesPlanted) {
		this.nonCoffeeTreesPlanted = nonCoffeeTreesPlanted;
	}

	public Boolean getSignsOfErosion() {
		return signsOfErosion;
	}

	public void setSignsOfErosion(Boolean signsOfErosion) {
		this.signsOfErosion = signsOfErosion;
	}

	public Boolean getErosionControlAdequate() {
		return erosionControlAdequate;
	}

	public void setErosionControlAdequate(Boolean erosionControlAdequate) {
		this.erosionControlAdequate = erosionControlAdequate;
	}

	public Boolean getBurningOfCropWaste() {
		return burningOfCropWaste;
	}

	public void setBurningOfCropWaste(Boolean burningOfCropWaste) {
		this.burningOfCropWaste = burningOfCropWaste;
	}

	public Boolean getFarmerHireLabour() {
		return farmerHireLabour;
	}

	public void setFarmerHireLabour(Boolean farmerHireLabour) {
		this.farmerHireLabour = farmerHireLabour;
	}

	public Boolean getIsLabourFairlyTreated() {
		return isLabourFairlyTreated;
	}

	public void setIsLabourFairlyTreated(Boolean isLabourFairlyTreated) {
		this.isLabourFairlyTreated = isLabourFairlyTreated;
	}

	public Boolean getIsChildLabourImployed() {
		return isChildLabourImployed;
	}

	public void setIsChildLabourImployed(Boolean isChildLabourImployed) {
		this.isChildLabourImployed = isChildLabourImployed;
	}

	public Quantity getPlasticDisposal() {
		return plasticDisposal;
	}

	public void setPlasticDisposal(Quantity plasticDisposal) {
		this.plasticDisposal = plasticDisposal;
	}

	public Boolean getIsOtherWasteDisposalAdequate() {
		return isOtherWasteDisposalAdequate;
	}

	public void setIsOtherWasteDisposalAdequate(Boolean isOtherWasteDisposalAdequate) {
		this.isOtherWasteDisposalAdequate = isOtherWasteDisposalAdequate;
	}

	public Boolean getIsHHMakingJointDecision() {
		return isHHMakingJointDecision;
	}

	public void setIsHHMakingJointDecision(Boolean isHHMakingJointDecision) {
		this.isHHMakingJointDecision = isHHMakingJointDecision;
	}

	public Boolean getIsHHTakingFarmingAsFamilyBusiness() {
		return isHHTakingFarmingAsFamilyBusiness;
	}

	public void setIsHHTakingFarmingAsFamilyBusiness(Boolean isHHTakingFarmingAsFamilyBusiness) {
		this.isHHTakingFarmingAsFamilyBusiness = isHHTakingFarmingAsFamilyBusiness;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Set<FarmPlot> getFarms() {
		return farms;
	}

	public void setFarms(Set<FarmPlot> farms) {
		for (FarmPlot farmPlot : farms)
			farmPlot.setInspection(this);
		this.farms = farms;
	}

	public Integer getNumberOfCoffeeFields() {
		return numberOfCoffeeFields;
	}

	public void setNumberOfCoffeeFields(Integer numberOfCoffeeFields) {
		this.numberOfCoffeeFields = numberOfCoffeeFields;
	}

	public Double getAreaUnderCoffee() {
		return areaUnderCoffee;
	}

	public void setAreaUnderCoffee(Double areaUnderCoffee) {
		this.areaUnderCoffee = areaUnderCoffee;
	}

	public Long getProductiveTrees() {
		return productiveTrees;
	}

	public void setProductiveTrees(Long productiveTrees) {
		this.productiveTrees = productiveTrees;
	}

	public Double getTotalAreaOfFarm() {
		return totalAreaOfFarm;
	}

	public void setTotalAreaOfFarm(Double totalAreaOfFarm) {
		this.totalAreaOfFarm = totalAreaOfFarm;
	}

	public Boolean getKnownToHarvestRipeCherries() {
		return knownToHarvestRipeCherries;
	}

	public void setKnownToHarvestRipeCherries(Boolean knownToHarvestRipeCherries) {
		this.knownToHarvestRipeCherries = knownToHarvestRipeCherries;
	}

	public Boolean getPracticesPostHarvestHandlling() {
		return practicesPostHarvestHandlling;
	}

	public void setPracticesPostHarvestHandlling(Boolean practicesPostHarvestHandlling) {
		this.practicesPostHarvestHandlling = practicesPostHarvestHandlling;
	}

	public Boolean getHasLiveStock() {
		return hasLiveStock;
	}

	public void setHasLiveStock(Boolean hasLiveStock) {
		this.hasLiveStock = hasLiveStock;
	}

	public Boolean getChemicalTreatmentOnLivestock() {
		return chemicalTreatmentOnLivestock;
	}

	public void setChemicalTreatmentOnLivestock(Boolean chemicalTreatmentOnLivestock) {
		this.chemicalTreatmentOnLivestock = chemicalTreatmentOnLivestock;
	}

	public Boolean getLivestockTreatmentConducted5mFromCoffee() {
		return livestockTreatmentConducted5mFromCoffee;
	}

	public void setLivestockTreatmentConducted5mFromCoffee(Boolean livestockTreatmentConducted5mFromCoffee) {
		this.livestockTreatmentConducted5mFromCoffee = livestockTreatmentConducted5mFromCoffee;
	}

	public Set<Animal> getAnimals() {
		return animals;
	}

	public void setAnimals(Set<Animal> animals) {
		for (Animal animal : animals)
			animal.setInspection(this);
		this.animals = animals;
	}

	public Decision getHasFarmerImplementedPreviousAdvice() {
		return hasFarmerImplementedPreviousAdvice;
	}

	public void setHasFarmerImplementedPreviousAdvice(Decision hasFarmerImplementedPreviousAdvice) {
		this.hasFarmerImplementedPreviousAdvice = hasFarmerImplementedPreviousAdvice;
	}

	public Set<Advice> getAdvices() {
		return advices;
	}

	public void setAdvices(Set<Advice> advices) {
		for (Advice advice : advices) {
			advice.setInspection(this);
		}
		this.advices = advices;
	}

	public Boolean getMadeSeriousViolation() {
		return madeSeriousViolation;
	}

	public void setMadeSeriousViolation(Boolean madeSeriousViolation) {
		this.madeSeriousViolation = madeSeriousViolation;
	}

	public Timestamp getViolationDate() {
		return violationDate;
	}

	public void setViolationDate(Timestamp violationDate) {
		this.violationDate = violationDate;
	}

	public Boolean getIsRecommendedOrganicCertificatation() {
		return isRecommendedOrganicCertificatation;
	}

	public void setIsRecommendedOrganicCertificatation(Boolean isRecommendedOrganicCertificatation) {
		this.isRecommendedOrganicCertificatation = isRecommendedOrganicCertificatation;
	}

	public Boolean getBoardAGMMinutesKept() {
		return boardAGMMinutesKept;
	}

	public void setBoardAGMMinutesKept(Boolean boardAGMMinutesKept) {
		this.boardAGMMinutesKept = boardAGMMinutesKept;
	}

	public Boolean getMembershipListsAndSharesUpdated() {
		return membershipListsAndSharesUpdated;
	}

	public void setMembershipListsAndSharesUpdated(Boolean membershipListsAndSharesUpdated) {
		this.membershipListsAndSharesUpdated = membershipListsAndSharesUpdated;
	}

	public Boolean getIsAnnualBudgetAndAuditedAccounts() {
		return isAnnualBudgetAndAuditedAccounts;
	}

	public void setIsAnnualBudgetAndAuditedAccounts(Boolean isAnnualBudgetAndAuditedAccounts) {
		this.isAnnualBudgetAndAuditedAccounts = isAnnualBudgetAndAuditedAccounts;
	}

	public Boolean getIsFairTradePremiumBudgetAndWorkplan() {
		return isFairTradePremiumBudgetAndWorkplan;
	}

	public void setIsFairTradePremiumBudgetAndWorkplan(Boolean isFairTradePremiumBudgetAndWorkplan) {
		this.isFairTradePremiumBudgetAndWorkplan = isFairTradePremiumBudgetAndWorkplan;
	}

	public Boolean getIsEnvirnmentCommitteAndItsWorkplan() {
		return isEnvirnmentCommitteAndItsWorkplan;
	}

	public void setIsEnvirnmentCommitteAndItsWorkplan(Boolean isEnvirnmentCommitteAndItsWorkplan) {
		this.isEnvirnmentCommitteAndItsWorkplan = isEnvirnmentCommitteAndItsWorkplan;
	}

	public Boolean getIsFTContractPersonAppointed() {
		return isFTContractPersonAppointed;
	}

	public void setIsFTContractPersonAppointed(Boolean isFTContractPersonAppointed) {
		this.isFTContractPersonAppointed = isFTContractPersonAppointed;
	}

	public CertificationStatus getCertificationStatus() {
		return certificationStatus;
	}

	public void setCertificationStatus(CertificationStatus certificationStatus) {
		this.certificationStatus = certificationStatus;
	}

	public CertificationVersion getCertificationVersion() {
		return certificationVersion;
	}

	public void setCertificationVersion(CertificationVersion certificationVersion) {
		this.certificationVersion = certificationVersion;
	}

	public String getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(String geoLocation) {
		this.geoLocation = geoLocation;
	}

	public Signature getFarmer() {
		return farmer;
	}

	public void setFarmer(Signature farmer) {
		this.farmer = farmer;
	}

	public Signature getFieldCoordinator() {
		return fieldCoordinator;
	}

	public void setFieldCoordinator(Signature fieldCoordinator) {
		this.fieldCoordinator = fieldCoordinator;
	}

	public Signature getIcsManager() {
		return icsManager;
	}

	public void setIcsManager(Signature icsManager) {
		this.icsManager = icsManager;
	}

	public Signature getChairPerson() {
		return chairPerson;
	}

	public void setChairPerson(Signature chairPerson) {
		this.chairPerson = chairPerson;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
