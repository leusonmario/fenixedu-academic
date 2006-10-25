<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html:xhtml/>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<h2><bean:message bundle="SPACE_RESOURCES" key="space.manager.page.title"/></h2>

<logic:messagesPresent message="true">
	<p>
		<span class="error0"><!-- Error messages go here -->
			<html:messages id="message" message="true" bundle="SPACE_RESOURCES">
				<bean:write name="message"/>
			</html:messages>
		</span>
	</p>
</logic:messagesPresent>

<ul>
	<li><html:link page="/showCreateSpaceForm.do"><bean:message bundle="SPACE_RESOURCES" key="link.create.space"/></html:link></li>
</ul>

<logic:present name="selectedSpace">
	<bean:write name="selectedSpace" property="idInternal"/>
</logic:present>

<logic:notEmpty name="spaces">
	<table class="tstyle4">
		<tr>
			<th>
				<bean:message bundle="SPACE_RESOURCES" key="title.type"/>
			</th>
			<th>
				<bean:message bundle="SPACE_RESOURCES" key="title.space.Space"/>
			</th>
			<th>
				<bean:message bundle="SPACE_RESOURCES" key="title.space.number.subspaces"/>
			</th>
			<th>
			</th>
		</tr>
		<logic:iterate id="space" name="spaces">
			<logic:notPresent name="space" property="suroundingSpace">
				<tr>
					<td>
						<logic:equal name="space" property="class.name" value="net.sourceforge.fenixedu.domain.space.Campus">
							<bean:message bundle="SPACE_RESOURCES" key="select.item.campus"/>
						</logic:equal>
						<logic:equal name="space" property="class.name" value="net.sourceforge.fenixedu.domain.space.Building">
							<bean:message bundle="SPACE_RESOURCES" key="select.item.building"/>
						</logic:equal>
					</td>
					<td>
						<html:link page="/manageSpaces.do?method=manageSpace&page=0" paramId="spaceInformationID" paramName="space" paramProperty="spaceInformation.idInternal">
							<logic:equal name="space" property="class.name" value="net.sourceforge.fenixedu.domain.space.Campus">
								<bean:write name="space" property="spaceInformation.presentationName"/>
							</logic:equal>
							<logic:equal name="space" property="class.name" value="net.sourceforge.fenixedu.domain.space.Building">
								<bean:write name="space" property="spaceInformation.presentationName"/>
							</logic:equal>
						</html:link>
					</td>
					<td class="acenter">
						<bean:write name="space" property="containedSpacesCount"/>
					</td>
					<td>
						<html:link page="/manageSpaces.do?method=deleteSpace&page=0" paramId="spaceID" paramName="space" paramProperty="idInternal">
							<bean:message bundle="SPACE_RESOURCES" key="link.delete.space"/>
						</html:link>
					</td>
				</tr>
			</logic:notPresent>
		</logic:iterate>
	</table>
</logic:notEmpty>
