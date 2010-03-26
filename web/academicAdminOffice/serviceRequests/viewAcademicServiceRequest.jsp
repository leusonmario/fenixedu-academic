<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/fenix-renderers.tld" prefix="fr"%>

<%@page import="net.sourceforge.fenixedu.domain.serviceRequests.AcademicServiceRequestSituation"%>

<html:xhtml/>

<bean:define id="academicServiceRequest" name="academicServiceRequest" type="net.sourceforge.fenixedu.domain.serviceRequests.RegistrationAcademicServiceRequest"/>

<em><bean:message key="label.academicAdminOffice" bundle="ACADEMIC_OFFICE_RESOURCES"/></em>
<h2 class="mbottom1"><bean:write name="academicServiceRequest" property="description"/></h2>

<html:messages id="messages" message="true">
	<p><span class="error0"><bean:write name="messages" bundle="ACADEMIC_OFFICE_RESOURCES"/></span></p>
</html:messages>

<p class="mbottom025"><strong><bean:message bundle="ACADEMIC_OFFICE_RESOURCES"  key="request.information"/></strong></p>
<bean:define id="simpleClassName" name="academicServiceRequest" property="class.simpleName" />
<fr:view name="academicServiceRequest" schema="<%= simpleClassName + ".view"%>">
	<fr:layout name="tabular">
		<fr:property name="classes" value="tstyle4 thright thlight mtop025"/>
		<fr:property name="rowClasses" value=",tdhl1,,,,,,,,"/>
	</fr:layout>
</fr:view>

<!--<logic:present name="academicServiceRequest" property="activeSituation">-->
<!--	<p class="mbottom025"><strong><bean:message bundle="ACADEMIC_OFFICE_RESOURCES" key="request.situation"/></strong></p>-->
<!--	<bean:define id="schema" name="academicServiceRequest" property="activeSituation.class.simpleName" />-->
<!--	<fr:view name="academicServiceRequest" property="activeSituation" schema="<%= schema.toString() + ".view" %>">-->
<!--		<fr:layout name="tabular">-->
<!--			<fr:property name="classes" value="tstyle4 thright thlight mtop025"/>-->
<!--		<fr:property name="rowClasses" value="tdhl1,,,"/>-->
<!--		</fr:layout>-->
<!--	</fr:view>-->
<!--</logic:present>-->

<logic:notEmpty name="serviceRequestSituations">
	<p class="mbottom025"><strong><bean:message bundle="ACADEMIC_OFFICE_RESOURCES" key="request.situation"/></strong></p>
	<fr:view name="serviceRequestSituations">

		<fr:schema bundle="ACADEMIC_OFFICE_RESOURCES" type="<%= AcademicServiceRequestSituation.class.getName() %>">
			<fr:slot name="academicServiceRequestSituationType" key="label.net.sourceforge.fenixedu.domain.serviceRequests.AcademicServiceRequest.academicServiceRequestSituationType">
			</fr:slot>
			<fr:slot name="finalSituationDate" key="label.date"/>
			<fr:slot name="employee" key="label.net.sourceforge.fenixedu.domain.serviceRequests.AcademicServiceRequest.employee.person.name" layout="null-as-label">
				<fr:property name="label" value=" - "/>
				<fr:property name="subLayout" value="values"/>
				<fr:property name="subSchema" value="responsibleEmployee.name"/>
			</fr:slot>
			<fr:slot name="justification" key="label.net.sourceforge.fenixedu.domain.serviceRequests.AcademicServiceRequest.justification" />
		</fr:schema>

		<fr:layout name="tabular">
			<fr:property name="classes" value="tstyle2 thlight" />
		</fr:layout>
	</fr:view>
</schema>

</logic:notEmpty>

<logic:equal name="canRevertToProcessingState" value="true">
	<fr:form action="/academicServiceRequestsManagement.do?method=revertRequestToProcessingState">
		<input type="hidden" name="academicServiceRequestId" value="<%= academicServiceRequest.getIdInternal().toString() %>" />
		<input type="hidden" name="registrationID" value="<%= academicServiceRequest.getIdInternal().toString() %>" />
		<input type="hidden" name="backAction" value="<%= request.getParameter("backAction") %>" />
		<input type="hidden" name="backMethod" value="<%= request.getParameter("backMethod") %>" />
		
		<html:submit><bean:message  key="label.academicServiceRequest.revert.to.processing.state" bundle="ACADEMIC_OFFICE_RESOURCES"/></html:submit>
	</fr:form>
</logic:equal>


<br/>
<br/>

<bean:define id="url" name="url" type="java.lang.String"/>
<html:form action="<%=url%>">
	<html:hidden bundle="HTMLALT_RESOURCES" altKey="hidden.academicServiceRequestId" property="academicServiceRequestId" value="<%= academicServiceRequest.getIdInternal().toString() %>"/>
	<html:hidden bundle="HTMLALT_RESOURCES" altKey="hidden.registrationID" property="registrationID" value="<%= academicServiceRequest.getRegistration().getIdInternal().toString() %>"/>
	<html:hidden bundle="HTMLALT_RESOURCES" altKey="hidden.registrationID" property="registrationID" value="<%= academicServiceRequest.getRegistration().getIdInternal().toString() %>"/>
	
	<html:submit><bean:message bundle="ACADEMIC_OFFICE_RESOURCES" key="back" /></html:submit>
</html:form>

