<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="org.apache.struts.action.Action" %>
<%@ page import="java.util.List" %>
<%@ page import="ServidorApresentacao.Action.sop.utils.SessionConstants" %>

<logic:present name="jspTitle">
	<h2><bean:write name="jspTitle" /></h2>
	<br />
	<bean:message key="title.studentListByDegree" />
</logic:present>
<logic:notPresent name="jspTitle">
	<h2><bean:message key="title.studentListByDegree" /></h2>
</logic:notPresent>

   <span class="error"><html:errors/></span>

    <bean:define id="masterDegreeList" name="<%= SessionConstants.MASTER_DEGREE_LIST %>" scope="request" />
    <bean:define id="link">/listMasterDegrees.do?method=chooseMasterDegree<%= "&" %>page=0<%= "&" %>degreeID=</bean:define>
    <p>
    <h3><%= ((List) masterDegreeList).size()%> <bean:message key="label.masterDegree.administrativeOffice.degreesFound"/></h3>        
    <% if (((List) masterDegreeList).size() != 0) { %>
    </p>
    <bean:message key="label.masterDegree.chooseOne"/><br><br>
	<bean:message key="label.manager.degrees" /><br>
   	     	<logic:iterate id="masterDegree" name="masterDegreeList">
        	<bean:define id="masterDegreeLink">
        		<bean:write name="link"/><bean:write name="masterDegree" property="idInternal"/>
        	</bean:define>
        	<html:link page='<%= pageContext.findAttribute("masterDegreeLink").toString() %>'>
				<bean:write name="masterDegree" property="nome"/> - 
				<bean:write name="masterDegree" property="sigla"/>
				<br>
            </html:link>
    	</logic:iterate>
	<% } %>