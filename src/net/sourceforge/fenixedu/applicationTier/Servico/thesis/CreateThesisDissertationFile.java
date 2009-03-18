package net.sourceforge.fenixedu.applicationTier.Servico.thesis;

import net.sourceforge.fenixedu.domain.accessControl.Group;
import net.sourceforge.fenixedu.domain.exceptions.DomainException;
import net.sourceforge.fenixedu.domain.person.RoleType;
import net.sourceforge.fenixedu.domain.research.result.ResearchResultDocumentFile;
import net.sourceforge.fenixedu.domain.research.result.ResearchResultDocumentFile.FileResultPermittedGroupType;
import net.sourceforge.fenixedu.domain.thesis.Thesis;
import net.sourceforge.fenixedu.domain.thesis.ThesisFile;
import net.sourceforge.fenixedu.injectionCode.AccessControl;
import pt.utl.ist.fenix.tools.util.i18n.Language;

public class CreateThesisDissertationFile extends CreateThesisFile {

    @Override
    protected void removePreviousFile(Thesis thesis) {
	ThesisFile dissertation = thesis.getDissertation();
	if (dissertation != null) {
	    if (AccessControl.getUserView().hasRoleType(RoleType.SCIENTIFIC_COUNCIL)) {
		dissertation.deleteWithoutStateCheck();
	    } else {
		dissertation.delete();
	    }
	}
    }

    @Override
    protected void updateThesis(Thesis thesis, ThesisFile file, String title, String subTitle, Language language) {
	if (title == null || subTitle == null) {
	    throw new DomainException("thesis.files.dissertation.title.required");
	}

	file.setTitle(title);
	file.setSubTitle(subTitle);
	file.setLanguage(language);

	thesis.setDissertation(file);

	final net.sourceforge.fenixedu.domain.research.result.publication.Thesis publication = thesis.getPublication();
	if (publication != null) {
	    final ResearchResultDocumentFile researchResultDocumentFile = publication.getResultDocumentFilesIterator().next();

	    final FileResultPermittedGroupType permittedGroupType = researchResultDocumentFile.getFileResultPermittedGroupType();
	    final Group permittedGroup = researchResultDocumentFile.getPermittedGroup();

	    researchResultDocumentFile.delete();

	    publication.addDocumentFile(file.getFilename(), file.getFilename(), permittedGroupType,
		    file.getMimeType(), file.getChecksum(), file.getChecksumAlgorithm(), file.getSize(),
		    file.getExternalStorageIdentification(), permittedGroup);
	    publication.setThesis(thesis);

	}
    }

}
