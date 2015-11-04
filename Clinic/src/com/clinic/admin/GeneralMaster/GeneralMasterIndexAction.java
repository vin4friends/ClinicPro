/**
 * 
 */
package com.clinic.admin.GeneralMaster;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.common.fe.impl.AbstractListAction;
import com.clinic.admin.bl.generalMaster.GeneralMasterAdministrationFacade;
import com.clinic.admin.model.impl.GeneralMaster;
import com.opensymphony.xwork2.ActionContext;


/**
 * @author Vineet
 * 
 */
public class GeneralMasterIndexAction extends AbstractListAction<GeneralMaster> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private transient GeneralMasterAdministrationFacade generalMasterAdministrationFacade;

	@Override
	public List<GeneralMaster> getList() {
		System.err.println("in here");
		return generalMasterAdministrationFacade.findAllGeneralMaster();
	}

	@Override
	protected int getPageSizeInternal() {
		return generalMasterAdministrationFacade.getPageSize();
	}
	
	public List<String> getGeneralMasterTypes(){
		System.err.println("in get masters");
		//System.err.println(generalMasterAdministrationFacade.getGeneralMasterTypes());
		return generalMasterAdministrationFacade.getGeneralMasterTypes();
	}
	
	public List<GeneralMaster> getGeneralMasterByType(){
		//ServletActionContext.getRequest().getParameter("");
		HttpServletRequest req = ServletActionContext.getRequest();
		
		System.err.println("--------------------------------------------------------" + req.getParameter("MasterType"));
		System.err.println(super.getText("masterType"));
		String masterType = req.getParameter("MasterType");
		
		if(StringUtils.isNotEmpty(masterType)){
			return generalMasterAdministrationFacade.findGeneralMasterByType(masterType);
		}
		
		return generalMasterAdministrationFacade.findGeneralMasterByType("RELIGION");
	}
	
	public @ResponseBody String byParameter(@RequestParam("masterType") String foo) {
	    return "Mapped by path + method + presence of query parameter! (MappingController) - foo = "
	           + foo;
	}
}
