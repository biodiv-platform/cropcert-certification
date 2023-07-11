package cropcert.certification.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cropcert.certification.pojo.Synchronization;
import cropcert.certification.pojo.response.ICSFarmerList;
import cropcert.entities.ApiException;

public interface SynchronizationService {

	public Synchronization save(HttpServletRequest request, String jsonString) throws IOException;

	public Synchronization save(Synchronization synchronization);

	public Synchronization update(Synchronization synchronization);

	public Synchronization findByPropertyWithCondtion(String property, Object value, String condition);

	public List<Synchronization> getByPropertyWithCondtion(String property, Object value, String condition, int limit,
			int offset);

	public List<ICSFarmerList> getSynchronizationForCollectionCenter(HttpServletRequest request, Integer limit,
			Integer offset, String ccCodes, Boolean isPendingOnly, String farmerName) throws ApiException;

	public Synchronization getReport(HttpServletRequest request, Integer version, Integer subVersion, Long farmerId);

	public List<Synchronization> getRecentSubversionforFarmers(HttpServletRequest request, Integer version,
			Long farmerId);
}
