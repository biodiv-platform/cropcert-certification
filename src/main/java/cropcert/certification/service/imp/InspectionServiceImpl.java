package cropcert.certification.service.imp;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.google.inject.Inject;

import cropcert.certification.dao.InspectionDao;
import cropcert.certification.dao.SynchronizationDao;
import cropcert.certification.pojo.Inspection;
import cropcert.certification.pojo.Signature;
import cropcert.certification.pojo.Synchronization;
import cropcert.certification.pojo.request.ICSSignRequest;
import cropcert.certification.pojo.response.FarmersInspectionReport;
import cropcert.certification.service.AbstractService;
import cropcert.certification.service.InspectionService;
import cropcert.certification.service.SynchronizationService;
import cropcert.certification.util.UserUtil;
import cropcert.user.ApiException;
import cropcert.user.api.CollectionCenterApi;
import cropcert.user.api.FarmerApi;
import cropcert.user.api.UserApi;
import cropcert.user.model.CollectionCenterShow;
import cropcert.user.model.Farmer;
import cropcert.user.model.User;

public class InspectionServiceImpl extends AbstractService<Inspection> implements InspectionService {

	@Inject
	private InspectionDao inspectorDao;

	@Inject
	private FarmerApi farmerApi;

	@Inject
	private CollectionCenterApi collectionCenterApi;

	@Inject
	private UserApi userApi;

	@Inject
	private InspectionService inspectionService;

	@Inject
	private SynchronizationService synchronizationService;

	@Inject
	private SynchronizationDao synchronizationDao;

	@Inject
	public InspectionServiceImpl(InspectionDao dao) {
		super(dao);
	}

	private String getInspectorName(Inspection inspection) {
		try {
			if (inspection != null) {
				User inspector = userApi.find(inspection.getInspectorId());
				if (inspector == null)
					return null;
				String firstName = inspector.getFirstName() == null ? "" : inspector.getFirstName();
				String lastName = inspector.getLastName() == null ? "" : inspector.getLastName();
				return firstName + " " + lastName;
			}
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public FarmersInspectionReport getFarmerInspectionReport(Long id) {
		Inspection inspection = super.findById(id);
		Synchronization sync = synchronizationService.findByPropertyWithCondtion("reportId", inspection.getId(), "=");
		Farmer farmer = null;
		try {
			farmer = farmerApi.find(inspection.getFarmerId());
		} catch (ApiException e) {
			e.printStackTrace();
		}
		String inspectorName = getInspectorName(inspection);
		return new FarmersInspectionReport(farmer, sync.getVersion(), sync.getSubVersion(), inspectorName, inspection);
	}

	@Override
	public List<Inspection> findAll(HttpServletRequest request, Integer limit, Integer offset) {
		return findAll(limit, offset);
	}

	@Override
	public FarmersInspectionReport save(HttpServletRequest request, Inspection inspection)
			throws IOException, ApiException {
		List<Inspection> inspections = new ArrayList<>();
		inspections.add(inspection);
		return bulkUpload(request, inspections).get(0);
	}

	@Override
	public List<FarmersInspectionReport> bulkUpload(HttpServletRequest request, List<Inspection> inspections)
			throws IOException, ApiException {

		List<FarmersInspectionReport> farmersInspectionReports = new ArrayList<>();
		for (Inspection inspection : inspections) {

			Long farmerId = inspection.getFarmerId();
			String inspectorIdString = UserUtil.getUserDetails(request).getId();
			Long inspectorId = Long.parseLong(inspectorIdString);

			FarmersInspectionReport farmersInspectionReport = inspectionService.getLatestFarmerReport(request,
					farmerId);

			Long updatedBy = inspectorId;

			Integer version;
			Integer subVersion;
			Boolean isReportFinalized = false;
			Timestamp lastUpdated = new Timestamp(new Date().getTime());
			Boolean isDeleted = false;

			if (farmersInspectionReport.getInspection() == null) {
				version = 0;
				subVersion = 1;
			} else { // Will definitely have the inspection report otherwise previous condition is
						// false.
				Long inspectionId = farmersInspectionReport.getInspection().getId();
				List<Synchronization> syncs = synchronizationService.getByPropertyWithCondtion("reportId", inspectionId,
						"=", -1, -1);

				Synchronization synchronization = syncs.get(0);
				version = synchronization.getVersion();
				subVersion = synchronization.getSubVersion() + 1;
			}

			inspection = save(inspection);
			Synchronization synchronization = new Synchronization(null, farmerId, inspection.getId(), version,
					subVersion, isReportFinalized, lastUpdated, updatedBy, isDeleted);
			synchronizationService.save(synchronization);

			farmersInspectionReport.setInspection(inspection);
			farmersInspectionReports.add(farmersInspectionReport);

		}
		return farmersInspectionReports;
	}

	@Override
	public List<FarmersInspectionReport> getAllReportsOfFarmer(HttpServletRequest request, Long farmerId)
			throws ApiException {
		Farmer farmer = farmerApi.find(farmerId);
		List<Inspection> inspections = inspectorDao.getByPropertyWithCondtion("farmerId", farmer.getId(), "=", -1, -1);

		List<FarmersInspectionReport> reports = new ArrayList<>();
		for (Inspection inspection : inspections) {
			Long inspectionId = inspection.getId();
			Synchronization syncs = synchronizationService.findByPropertyWithCondtion("reportId", inspectionId, "=");

			String inspectorName = getInspectorName(inspection);
			FarmersInspectionReport report = new FarmersInspectionReport(farmer, syncs.getVersion(),
					syncs.getSubVersion(), inspectorName, inspection);
			reports.add(report);
		}
		return reports;
	}

	@Override
	public List<Inspection> getReportsForInspector(HttpServletRequest request, Integer limit, Integer offset,
			Long inspectorId, Long farmerId) {
		return inspectorDao.getReportsForInspector(limit, offset, inspectorId, farmerId);
	}

	@Override
	public Collection<FarmersInspectionReport> getReportsForCooperative(HttpServletRequest request, Integer limit,
			Integer offset, Long coCode) throws ApiException {
		List<CollectionCenterShow> collectionCenters = collectionCenterApi.findAll_0(coCode);
		StringBuilder ccCodes = new StringBuilder();

		if (collectionCenters.isEmpty())
			return new ArrayList<>();

		ccCodes.append(collectionCenters.get(0).getId());
		for (int i = 1; i < collectionCenters.size(); i++) {
			ccCodes.append(",");
			ccCodes.append(collectionCenters.get(i).getId());
		}

		List<Farmer> farmers = farmerApi.getFarmerForMultipleCollectionCenter(ccCodes.toString(), null, limit,
				offset);
		return getLatestReportForFarmers(farmers, limit, offset);
	}

	@Override
	public Collection<FarmersInspectionReport> getReportsForCollectionCenter(HttpServletRequest request, Integer limit,
			Integer offset, Long ccCode) {

		List<Farmer> farmers = new ArrayList<>();
		try {
			if (ccCode != -1) {
				farmers = farmerApi.getFarmerForCollectionCenter(ccCode, limit, offset);
			} else {
				farmers = farmerApi.findAll(limit, offset);
			}
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return getLatestReportForFarmers(farmers, limit, offset);
	}

	@Override
	public FarmersInspectionReport getLatestFarmerReport(HttpServletRequest request, Long farmerId)
			throws ApiException {

		Farmer farmer = farmerApi.find(farmerId);
		List<Farmer> farmers = new ArrayList<>();
		farmers.add(farmer);
		Collection<FarmersInspectionReport> reports = getLatestReportForFarmers(farmers, -1, -1);
		return reports.iterator().next();
	}

	private Collection<FarmersInspectionReport> getLatestReportForFarmers(List<Farmer> farmers, Integer limit,
			Integer offset) {
		Map<Long, FarmersInspectionReport> reports = new HashMap<>();
		Set<Long> farmerIds = new HashSet<>();
		for (Farmer farmer : farmers) {
			Long id = farmer.getId();
			farmerIds.add(id);
			FarmersInspectionReport farmersLastReport = new FarmersInspectionReport(farmer, 0, 0, null, null);
			reports.put(id, farmersLastReport);
		}

		List<Synchronization> syncs = synchronizationDao.getSynchronizationForFarmers(limit, offset, farmerIds);

		for (Synchronization sync : syncs) {
			Long farmerId = sync.getFarmerId();
			FarmersInspectionReport farmersInspectionReport = reports.get(farmerId);
			Inspection inspection = inspectorDao.findById(sync.getReportId());
			farmersInspectionReport.setInspectorName(getInspectorName(inspection));
			farmersInspectionReport.setInspection(inspection);
			farmersInspectionReport.setVersion(syncs.get(0).getVersion());
			farmersInspectionReport.setSubVersion(syncs.get(0).getSubVersion());
		}

		return reports.values();
	}

	@Override
	public FarmersInspectionReport signByICSManager(HttpServletRequest request, ICSSignRequest icsSignRequest)
			throws ApiException {
		Long farmerId = icsSignRequest.getFarmerId();
		Integer version = icsSignRequest.getVersion();
		Integer subVersion = icsSignRequest.getSubVersion();
		Timestamp currentTime = new Timestamp(new Date().getTime());

		List<Synchronization> prevSyncVersions = synchronizationService.getRecentSubversionforFarmers(request, version,
				farmerId);
		for (Synchronization prevSyncVersion : prevSyncVersions) {
			prevSyncVersion.setIsReportFinalized(true);
			prevSyncVersion.setLastUpdated(currentTime);
			synchronizationService.update(prevSyncVersion);
		}

		Synchronization syncEntry = synchronizationService.getReport(request, icsSignRequest.getVersion(),
				icsSignRequest.getSubVersion(), farmerId);
		syncEntry.setVersion(version + 1);
		syncEntry.setSubVersion(0);
		syncEntry.setLastUpdated(currentTime);
		syncEntry.setIsReportFinalized(true);
		synchronizationService.update(syncEntry);

		Long inspectionId = syncEntry.getReportId();
		Inspection inspection = inspectionService.findById(inspectionId);
		Signature icsSign = icsSignRequest.getSignature();
		if (icsSign.getDate() == null)
			icsSign.setDate(currentTime);
		inspection.setIcsManager(icsSign);
		inspection = update(inspection);

		Farmer farmer = farmerApi.find(farmerId);
		String inspectorName = getInspectorName(inspection);
		return new FarmersInspectionReport(farmer, version, subVersion, inspectorName, inspection);
	}

	@Override
	public List<FarmersInspectionReport> bulkReportsSignByICSManager(HttpServletRequest request,
			List<ICSSignRequest> icsSignRequests) throws NumberFormatException, ApiException {

		List<FarmersInspectionReport> inspections = new ArrayList<>();
		for (ICSSignRequest icsSignRequest : icsSignRequests) {
			inspections.add(signByICSManager(request, icsSignRequest));
		}

		return inspections;
	}
}
