package net.sourceforge.fenixedu.applicationTier.Servico.publico;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import net.sourceforge.fenixedu.applicationTier.Service;
import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.FenixServiceException;
import net.sourceforge.fenixedu.dataTransferObject.InfoExecutionDegree;
import net.sourceforge.fenixedu.domain.Degree;
import net.sourceforge.fenixedu.domain.ExecutionDegree;
import net.sourceforge.fenixedu.domain.ExecutionSemester;
import net.sourceforge.fenixedu.domain.ExecutionYear;

/**
 * @author T�nia Pous�o Create on 13/Nov/2003
 */
public class ReadExecutionDegreesByDegreeAndExecutionPeriod extends Service {

    public List<InfoExecutionDegree> run(Integer executionPeriodId, Integer degreeId) throws FenixServiceException {
	if (degreeId == null) {
	    throw new FenixServiceException("error.impossibleDegreeSite");
	}

	// Execution OccupationPeriod
	ExecutionSemester executionSemester;
	if (executionPeriodId == null) {
	    executionSemester = ExecutionSemester.readActualExecutionSemester();
	} else {
	    executionSemester = rootDomainObject.readExecutionSemesterByOID(executionPeriodId);
	}

	if (executionSemester == null) {
	    throw new FenixServiceException("error.impossibleDegreeSite");
	}

	ExecutionYear executionYear = executionSemester.getExecutionYear();
	if (executionYear == null) {
	    throw new FenixServiceException("error.impossibleDegreeSite");
	}

	// Degree
	Degree degree = rootDomainObject.readDegreeByOID(degreeId);
	if (degree == null) {
	    throw new FenixServiceException("error.impossibleDegreeSite");
	}

	// Execution degrees
	List<ExecutionDegree> executionDegreeList = ExecutionDegree.getAllByDegreeAndExecutionYear(degree, executionYear
		.getYear());
	if (executionDegreeList == null || executionDegreeList.size() <= 0) {
	    throw new FenixServiceException("error.impossibleDegreeSite");
	}

	List<InfoExecutionDegree> result = new ArrayList<InfoExecutionDegree>();
	ListIterator listIterator = executionDegreeList.listIterator();
	while (listIterator.hasNext()) {
	    ExecutionDegree executionDegree = (ExecutionDegree) listIterator.next();

	    InfoExecutionDegree infoExecutionDegree = InfoExecutionDegree.newInfoFromDomain(executionDegree);

	    result.add(infoExecutionDegree);
	}

	return result;
    }
}