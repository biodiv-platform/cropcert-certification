/**
 * 
 */
package cropcert.certification.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cropcert.certification.ApiConstants;
import cropcert.certification.filter.Permissions;
import cropcert.certification.filter.TokenAndUserAuthenticated;
import cropcert.certification.pojo.Inspection;
import cropcert.certification.pojo.Synchronization;
import cropcert.certification.pojo.response.ICSFarmerList;
import cropcert.certification.service.SynchronizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author vilay
 *
 */
@Path(ApiConstants.SYNC)
@Api("Synchronization")
public class SynchronizationController {

	@Inject
	private SynchronizationService synchronizationService;

	@Path("all/ccCode")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get all the synchronization entry for collection center", response = ICSFarmerList.class, responseContainer = "List")
	public Response getAllByCCCode(@Context HttpServletRequest request,
			@DefaultValue("-1") @QueryParam("limit") Integer limit,
			@DefaultValue("-1") @QueryParam("offset") Integer offset,
			@DefaultValue("-1") @QueryParam("ccCodes") String ccCodes,
			@DefaultValue("false") @QueryParam("pendingReportOnly") Boolean pendingReportOnly,
			@QueryParam("firstName") String firstName) {
		try {
			List<ICSFarmerList> reports = synchronizationService.getSynchronizationForCollectionCenter(request, limit,
					offset, ccCodes, pendingReportOnly, firstName);
			return Response.ok().entity(reports).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Add sync entry", notes = "Returns succuess failure", response = Inspection.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Add the sync entry", response = String.class),
			@ApiResponse(code = 500, message = "ERROR", response = String.class) })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
	@TokenAndUserAuthenticated(permissions = { Permissions.INSPECTOR })
	public Response addSyncEntry(@Context HttpServletRequest request, String jsonString) {
		try {
			Synchronization synchronization = synchronizationService.save(request, jsonString);
			return Response.ok().entity(synchronization).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}

	@Path("report")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get all the synchronization entry for farmer", response = Synchronization.class, responseContainer = "List")
	public Response getReport(@Context HttpServletRequest request,
			@DefaultValue("-1") @QueryParam("limit") Integer version,
			@DefaultValue("-1") @QueryParam("offset") Integer subVersion,
			@DefaultValue("-1") @QueryParam("farmerId") Long farmerId) {
		try {
			Synchronization synchronization = synchronizationService.getReport(request, version, subVersion, farmerId);
			return Response.ok().entity(synchronization).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}

	@Path("report/recent")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get all recent subversion entry of the farmer", response = Synchronization.class, responseContainer = "List")
	public Response getReport(@Context HttpServletRequest request,
			@DefaultValue("-1") @QueryParam("version") Integer version,
			@DefaultValue("-1") @QueryParam("farmerId") Long farmerId) {
		try {
			List<Synchronization> synchronizations = synchronizationService.getRecentSubversionforFarmers(request,
					version, farmerId);
			return Response.ok().entity(synchronizations).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}
}
