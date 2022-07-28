/**
 * 
 */
package cropcert.certification.controller;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cropcert.certification.ApiConstants;
import cropcert.certification.pojo.Inspection;
import cropcert.certification.pojo.request.ICSSignRequest;
import cropcert.certification.pojo.response.FarmersInspectionReport;
import cropcert.certification.service.InspectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author vilay
 *
 */
@Path(ApiConstants.INSPECTION)
@Api("Inspection")
public class InspectionController {
	
	@Inject
	private InspectionService inspectionService;

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get Inspection report by Id", response = FarmersInspectionReport.class)
	public Response findById(@Context HttpServletRequest request, @PathParam("id") Long id) {
		try {
			FarmersInspectionReport inspection = inspectionService.getFarmerInspectionReport(id);
			return Response.ok().entity(inspection).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}

	@Path("all/coCode")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get all the inspection report from collection center given by ccCode", response = FarmersInspectionReport.class, responseContainer = "List")
	public Response getAllByCOCode(@Context HttpServletRequest request,
			@DefaultValue("-1") @QueryParam("limit") Integer limit,
			@DefaultValue("-1") @QueryParam("offset") Integer offset,
			@DefaultValue("-1") @QueryParam("coCode") String coCode) {
		try {
			Collection<FarmersInspectionReport> reports = inspectionService.getReportsForCooperative(request, limit,
					offset, coCode);
			return Response.ok().entity(reports).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}

	@Path("all/ccCode")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get all the inspection report from collection center given by ccCode", response = FarmersInspectionReport.class, responseContainer = "List")
	public Response getAllByCCCode(@Context HttpServletRequest request,
			@DefaultValue("-1") @QueryParam("limit") Integer limit,
			@DefaultValue("-1") @QueryParam("offset") Integer offset,
			@DefaultValue("-1") @QueryParam("ccCode") Long ccCode) {
		try {
			Collection<FarmersInspectionReport> reports = inspectionService.getReportsForCollectionCenter(request,
					limit, offset, ccCode);
			return Response.ok().entity(reports).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}

	@Path("farmer/latest")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get latest report of the farmer", response = FarmersInspectionReport.class)
	public Response getLatestFarmerReport(@Context HttpServletRequest request,
			@DefaultValue("-1") @QueryParam("farmerId") Long farmerId) {
		try {
			FarmersInspectionReport reports = inspectionService.getLatestFarmerReport(request, farmerId);
			return Response.ok().entity(reports).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}

	@Path("farmer/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get all the inspection report of a single farmer", response = FarmersInspectionReport.class, responseContainer = "List")
	public Response findAllFarmerReport(@Context HttpServletRequest request,
			@DefaultValue("-1") @QueryParam("farmerId") Long farmerId) {
		try {
			List<FarmersInspectionReport> reports = inspectionService.getAllReportsOfFarmer(request, farmerId);
			return Response.ok().entity(reports).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Add inspection report", notes = "Returns succuess failure", response = FarmersInspectionReport.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Could not add inspection report", response = String.class),
			@ApiResponse(code = 500, message = "ERROR", response = String.class) })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
	public Response addInspection(@Context HttpServletRequest request,
			@ApiParam(name = "inspection") Inspection inspection) {
		try {
			FarmersInspectionReport farmersInspectionReport = inspectionService.save(request, inspection);
			return Response.ok().entity(farmersInspectionReport).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}

	@POST
	@Path("bulk")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Add inspection report", notes = "Returns succuess failure", response = FarmersInspectionReport.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Could not add inspection report", response = String.class),
			@ApiResponse(code = 500, message = "ERROR", response = String.class) })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
	public Response bulkUpload(@Context HttpServletRequest request,
			@ApiParam(name = "inspections") List<Inspection> inspections) {
		try {
			List<FarmersInspectionReport> inspection = inspectionService.bulkUpload(request, inspections);
			return Response.ok().entity(inspection).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}

	@POST
	@Path("ics/sign")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Add sign of ICS manager", notes = "Returns succuess failure", response = FarmersInspectionReport.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Could not add sign of the ics manager", response = String.class),
			@ApiResponse(code = 500, message = "ERROR", response = String.class) })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
	public Response signByICSManager(@Context HttpServletRequest request,
			@ApiParam(name = "ICSSignRequest") ICSSignRequest icsSignRequest) {
		try {
			FarmersInspectionReport inspection = inspectionService.signByICSManager(request, icsSignRequest);
			return Response.ok().entity(inspection).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}

	@POST
	@Path("ics/bulk/sign")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Add sign of ICS manager", notes = "Returns succuess failure", response = FarmersInspectionReport.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Could not add sign of the ics manager", response = String.class),
			@ApiResponse(code = 500, message = "ERROR", response = String.class) })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
	public Response bulkReportsSignByICSManager(@Context HttpServletRequest request,
			@ApiParam(name = "ICSSignRequests") List<ICSSignRequest> icsSignRequest) {
		try {
			List<FarmersInspectionReport> inspections = inspectionService.bulkReportsSignByICSManager(request, icsSignRequest);
			return Response.ok().entity(inspections).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}

}
