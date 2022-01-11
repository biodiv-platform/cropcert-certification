package cropcert.certification.controller.impl;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.google.inject.Inject;

import cropcert.certification.controller.InspectionController;
import cropcert.certification.filter.Permissions;
import cropcert.certification.filter.TokenAndUserAuthenticated;
import cropcert.certification.pojo.Inspection;
import cropcert.certification.pojo.request.ICSSignRequest;
import cropcert.certification.pojo.response.FarmersInspectionReport;
import cropcert.certification.service.InspectionService;
import io.swagger.annotations.ApiParam;

public class InspectionControllerImpl implements InspectionController {

	@Inject
	private InspectionService inspectionService;

	@Override
	public Response findById(@Context HttpServletRequest request, Long id) {
		try {
			FarmersInspectionReport inspection = inspectionService.getFarmerInspectionReport(id);
			return Response.ok().entity(inspection).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}
	
	@Override
	public Response getAllByCOCode(HttpServletRequest request, Integer limit, Integer offset, Long coCode) {
		try {
			Collection<FarmersInspectionReport> reports = inspectionService.getReportsForCooperative(request, limit, offset, coCode);
			return Response.ok().entity(reports).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}

	@Override
	public Response getAllByCCCode(HttpServletRequest request, Integer limit, Integer offset, Long ccCode) {
		try {
			Collection<FarmersInspectionReport> reports = inspectionService.getReportsForCollectionCenter(request, limit, offset, ccCode);
			return Response.ok().entity(reports).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}
	
	@Override
	public Response getLatestFarmerReport(HttpServletRequest request, Long farmerId) {
		try {
			FarmersInspectionReport reports = inspectionService.getLatestFarmerReport(request, farmerId);
			return Response.ok().entity(reports).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}
	
	@Override
	public Response findAllFarmerReport(HttpServletRequest request, Long farmerId) {
		try {
			List<FarmersInspectionReport> reports = inspectionService.getAllReportsOfFarmer(request, farmerId);
			return Response.ok().entity(reports).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}
	
	@Override
	@TokenAndUserAuthenticated(permissions = { Permissions.INSPECTOR })
	public Response bulkUpload(@Context HttpServletRequest request, @ApiParam(name = "inspections") List<Inspection> inspections) {
		try {
			List<FarmersInspectionReport> inspection = inspectionService.bulkUpload(request, inspections);
			return Response.ok().entity(inspection).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}

	@Override
	@TokenAndUserAuthenticated(permissions = { Permissions.INSPECTOR, Permissions.UNION, Permissions.CO_PERSON })
	public Response addInspection(@Context HttpServletRequest request, @ApiParam(name = "inspection") Inspection inspection) {
		try {
			FarmersInspectionReport farmersInspectionReport = inspectionService.save(request, inspection);
			return Response.ok().entity(farmersInspectionReport).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}

	@Override
	@TokenAndUserAuthenticated(permissions = {Permissions.ICS_MANAGER})
	public Response signByICSManager(@Context HttpServletRequest request, @ApiParam(name = "ICSSignRequest") ICSSignRequest icsSignRequest) {
		try {
			FarmersInspectionReport inspection = inspectionService.signByICSManager(request, icsSignRequest);
			return Response.ok().entity(inspection).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}
	
	@Override
	@TokenAndUserAuthenticated(permissions = {Permissions.ICS_MANAGER})
	public Response bulkReportsSignByICSManager(@Context HttpServletRequest request, @ApiParam(name = "ICSSignRequest") List<ICSSignRequest> icsSignRequests) {
		try {
			List<FarmersInspectionReport> inspections = inspectionService.bulkReportsSignByICSManager(request, icsSignRequests);
			return Response.ok().entity(inspections).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}
}
