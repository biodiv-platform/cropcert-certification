/**
 * 
 */
package cropcert.certification.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cropcert.certification.ApiConstants;
import cropcert.certification.pojo.Inspection;
import cropcert.certification.pojo.request.ICSSignRequest;
import cropcert.certification.pojo.response.FarmersInspectionReport;
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
public interface InspectionController {

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get Inspection report by Id", response = FarmersInspectionReport.class)
	public Response findById(@Context HttpServletRequest request, @PathParam("id") Long id);

	@Path("all/coCode")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get all the inspection report from collection center given by ccCode", response = FarmersInspectionReport.class, responseContainer = "List")
	public Response getAllByCOCode(@Context HttpServletRequest request,
			@DefaultValue("-1") @QueryParam("limit") Integer limit,
			@DefaultValue("-1") @QueryParam("offset") Integer offset,
			@DefaultValue("-1") @QueryParam("coCode") String coCode);
	
	@Path("all/ccCode")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get all the inspection report from collection center given by ccCode", response = FarmersInspectionReport.class, responseContainer = "List")
	public Response getAllByCCCode(@Context HttpServletRequest request,
			@DefaultValue("-1") @QueryParam("limit") Integer limit,
			@DefaultValue("-1") @QueryParam("offset") Integer offset,
			@DefaultValue("-1") @QueryParam("ccCode") Long ccCode);

	@Path("farmer/latest")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get latest report of the farmer", response = FarmersInspectionReport.class)
	public Response getLatestFarmerReport(@Context HttpServletRequest request,
			@DefaultValue("-1") @QueryParam("farmerId") Long farmerId);

	@Path("farmer/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get all the inspection report of a single farmer", response = FarmersInspectionReport.class, responseContainer = "List")
	public Response findAllFarmerReport(@Context HttpServletRequest request,
			@DefaultValue("-1") @QueryParam("farmerId") Long farmerId);

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Add inspection report", notes = "Returns succuess failure", response = FarmersInspectionReport.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Could not add inspection report", response = String.class),
			@ApiResponse(code = 500, message = "ERROR", response = String.class) })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
	public Response addInspection(@Context HttpServletRequest request, @ApiParam(name = "inspection") Inspection inspection);

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
	public Response bulkUpload(@Context HttpServletRequest request, @ApiParam(name = "inspections") List<Inspection> inspections);

	@POST
	@Path("ics/sign")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Add sign of ICS manager", notes = "Returns succuess failure", response = FarmersInspectionReport.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Could not add sign of the ics manager", response = String.class),
			@ApiResponse(code = 500, message = "ERROR", response = String.class) })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
	public Response signByICSManager(@Context HttpServletRequest request, @ApiParam(name = "ICSSignRequest") ICSSignRequest icsSignRequest);
	
	@POST
	@Path("ics/bulk/sign")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Add sign of ICS manager", notes = "Returns succuess failure", response = FarmersInspectionReport.class, responseContainer="List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Could not add sign of the ics manager", response = String.class),
			@ApiResponse(code = 500, message = "ERROR", response = String.class) })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
	public Response bulkReportsSignByICSManager(@Context HttpServletRequest request, @ApiParam(name = "ICSSignRequests") List<ICSSignRequest> icsSignRequest);


}
