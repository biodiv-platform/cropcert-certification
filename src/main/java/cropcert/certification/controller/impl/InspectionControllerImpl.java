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
import cropcert.certification.pojo.response.FarmersInspectionReport;
import cropcert.certification.service.InspectionService;

public class InspectionControllerImpl implements InspectionController {

	@Inject
	private InspectionService inspectionService;

	@Override
	public Response findById(@Context HttpServletRequest request, Long id) {
		try {
			Inspection inspection = inspectionService.findById(id);
			return Response.ok().entity(inspection).build();
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
			List<FarmersInspectionReport> reports = inspectionService.getAllFarmerReport(request, farmerId);
			return Response.ok().entity(reports).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}

	@Override
	@TokenAndUserAuthenticated(permissions = { Permissions.INSPECTOR })
	public Response addInspection(@Context HttpServletRequest request, String jsonString) {
		try {
			Inspection inspection = inspectionService.save(request, jsonString);
			return Response.ok().entity(inspection).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}

}
