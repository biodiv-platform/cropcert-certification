package cropcert.certification.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

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

import cropcert.certification.pojo.enumtype.FieldSeparationType;
import cropcert.certification.pojo.enumtype.Quality;
import io.swagger.annotations.ApiModel;

@Entity
@Table(name = "farm_plot")
@JsonIgnoreProperties
@ApiModel("FarmPlot")
public class FarmPlot implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4636368184012471812L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "farm_plot_id_generator")
	@SequenceGenerator(name = "farm_plot_id_generator", sequenceName = "farm_plot_id_seq", allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "fieldName")
	private String fieldName;
	@Column(name = "acres")
	private Double acres;
	@Column(name = "main_crop")
	private String mainCrop;
	@Column(name = "intercrops")
	private String intercrops;
	@Column(name = "number_of_coffe_trees")
	private Long numberOfCoffeTrees;
	@Column(name = "yeild_estimate")
	private Double yeildEstimate;
	@Column(name = "area_under_coffee")
	private Double areaUnderCoffee;
	@Column(name = "is_coffee_tree_well_maintained")
	private Boolean isCoffeeTreeWellMaintained;
	@Column(name = "pruining")
	private Quality pruining;
	@Column(name = "number_of_pruined_coffee_trees")
	private Long numberOfPruinedCoffeeTrees;
	@Column(name = "stumping")
	private Quality stumping;
	@Column(name = "number_of_stumped_tree")
	private Long numberOfStumpedTree;
	@Column(name = "planting_new_coffee_seedings")
	private Boolean plantingNewCoffeeSeedings;
	@Column(name = "last_use_of_non_allowed_chemicals")
	private Timestamp lastUseOfNonAllowedChemicals;
	@Column(name = "inter_plot_buffer_zones")
	private Boolean interPlotBufferZones;
	@Column(name = "field_separation")
	private FieldSeparationType fieldSeparation;
	@Column(name = "multiple_owner_with_organic")
	private Boolean multipleOwnerWithOrganic;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "inspection_id")
	private Inspection inspection;

	public FarmPlot() {
		super();
	}

	public FarmPlot(Long id, String fieldName, Double acres, String mainCrop, String intercrops,
			Long numberOfCoffeTrees, Double yeildEstimate, Double areaUnderCoffee, Boolean isCoffeeTreeWellMaintained,
			Quality pruining, Long numberOfPruinedCoffeeTrees, Quality stumping, Long numberOfStumpedTree,
			Boolean plantingNewCoffeeSeedings, Timestamp lastUseOfNonAllowedChemicals, Boolean interPlotBufferZones,
			FieldSeparationType fieldSeparation, Boolean multipleOwnerWithOrganic, Inspection inspection) {
		super();
		this.id = id;
		this.fieldName = fieldName;
		this.acres = acres;
		this.mainCrop = mainCrop;
		this.intercrops = intercrops;
		this.numberOfCoffeTrees = numberOfCoffeTrees;
		this.yeildEstimate = yeildEstimate;
		this.areaUnderCoffee = areaUnderCoffee;
		this.isCoffeeTreeWellMaintained = isCoffeeTreeWellMaintained;
		this.pruining = pruining;
		this.numberOfPruinedCoffeeTrees = numberOfPruinedCoffeeTrees;
		this.stumping = stumping;
		this.numberOfStumpedTree = numberOfStumpedTree;
		this.plantingNewCoffeeSeedings = plantingNewCoffeeSeedings;
		this.lastUseOfNonAllowedChemicals = lastUseOfNonAllowedChemicals;
		this.interPlotBufferZones = interPlotBufferZones;
		this.fieldSeparation = fieldSeparation;
		this.multipleOwnerWithOrganic = multipleOwnerWithOrganic;
		this.inspection = inspection;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Double getAcres() {
		return acres;
	}

	public void setAcres(Double acres) {
		this.acres = acres;
	}

	public String getMainCrop() {
		return mainCrop;
	}

	public void setMainCrop(String mainCrop) {
		this.mainCrop = mainCrop;
	}

	public String getIntercrops() {
		return intercrops;
	}

	public void setIntercrops(String intercrops) {
		this.intercrops = intercrops;
	}

	public Long getNumberOfCoffeTrees() {
		return numberOfCoffeTrees;
	}

	public void setNumberOfCoffeTrees(Long numberOfCoffeTrees) {
		this.numberOfCoffeTrees = numberOfCoffeTrees;
	}

	public Double getYeildEstimate() {
		return yeildEstimate;
	}

	public void setYeildEstimate(Double yeildEstimate) {
		this.yeildEstimate = yeildEstimate;
	}

	public Double getAreaUnderCoffee() {
		return areaUnderCoffee;
	}

	public void setAreaUnderCoffee(Double areaUnderCoffee) {
		this.areaUnderCoffee = areaUnderCoffee;
	}

	public Boolean getIsCoffeeTreeWellMaintained() {
		return isCoffeeTreeWellMaintained;
	}

	public void setIsCoffeeTreeWellMaintained(Boolean isCoffeeTreeWellMaintained) {
		this.isCoffeeTreeWellMaintained = isCoffeeTreeWellMaintained;
	}

	public Quality getPruining() {
		return pruining;
	}

	public void setPruining(Quality pruining) {
		this.pruining = pruining;
	}

	public Long getNumberOfPruinedCoffeeTrees() {
		return numberOfPruinedCoffeeTrees;
	}

	public void setNumberOfPruinedCoffeeTrees(Long numberOfPruinedCoffeeTrees) {
		this.numberOfPruinedCoffeeTrees = numberOfPruinedCoffeeTrees;
	}

	public Quality getStumping() {
		return stumping;
	}

	public void setStumping(Quality stumping) {
		this.stumping = stumping;
	}

	public Long getNumberOfStumpedTree() {
		return numberOfStumpedTree;
	}

	public void setNumberOfStumpedTree(Long numberOfStumpedTree) {
		this.numberOfStumpedTree = numberOfStumpedTree;
	}

	public Boolean getPlantingNewCoffeeSeedings() {
		return plantingNewCoffeeSeedings;
	}

	public void setPlantingNewCoffeeSeedings(Boolean plantingNewCoffeeSeedings) {
		this.plantingNewCoffeeSeedings = plantingNewCoffeeSeedings;
	}

	public Timestamp getLastUseOfNonAllowedChemicals() {
		return lastUseOfNonAllowedChemicals;
	}

	public void setLastUseOfNonAllowedChemicals(Timestamp lastUseOfNonAllowedChemicals) {
		this.lastUseOfNonAllowedChemicals = lastUseOfNonAllowedChemicals;
	}

	public Boolean getInterPlotBufferZones() {
		return interPlotBufferZones;
	}

	public void setInterPlotBufferZones(Boolean interPlotBufferZones) {
		this.interPlotBufferZones = interPlotBufferZones;
	}

	public FieldSeparationType getFieldSeparation() {
		return fieldSeparation;
	}

	public void setFieldSeparation(FieldSeparationType fieldSeparation) {
		this.fieldSeparation = fieldSeparation;
	}

	public Boolean getMultipleOwnerWithOrganic() {
		return multipleOwnerWithOrganic;
	}

	public void setMultipleOwnerWithOrganic(Boolean multipleOwnerWithOrganic) {
		this.multipleOwnerWithOrganic = multipleOwnerWithOrganic;
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
