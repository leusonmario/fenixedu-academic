package ServidorAplicacao.Servico.student;

import java.util.List;

import Dominio.IDisciplinaExecucao;
import Dominio.IFrequenta;
import Dominio.IGroupProperties;
import Dominio.IStudent;
import Dominio.IStudentGroup;
import Dominio.IStudentGroupAttend;
import Dominio.StudentGroup;
import ServidorAplicacao.IServico;
import ServidorAplicacao.Servico.exceptions.FenixServiceException;
import ServidorAplicacao.strategy.groupEnrolment.strategys.GroupEnrolmentStrategyFactory;
import ServidorAplicacao.strategy.groupEnrolment.strategys.IGroupEnrolmentStrategy;
import ServidorAplicacao.strategy.groupEnrolment.strategys.IGroupEnrolmentStrategyFactory;
import ServidorPersistente.ExcepcaoPersistencia;
import ServidorPersistente.IFrequentaPersistente;
import ServidorPersistente.IPersistentStudent;
import ServidorPersistente.IPersistentStudentGroup;
import ServidorPersistente.IPersistentStudentGroupAttend;
import ServidorPersistente.ISuportePersistente;
import ServidorPersistente.OJB.SuportePersistenteOJB;

/**
 * @author asnr and scpo
 *
 */

public class UnEnrollStudentInGroup implements IServico {

	private static UnEnrollStudentInGroup _servico = new UnEnrollStudentInGroup();
	
	/**
	 * The singleton access method of this class.
	 **/
	
	public static UnEnrollStudentInGroup getService() {
		return _servico;
	}

	/**
	 * The actor of this class.
	 **/
	private UnEnrollStudentInGroup() {
	}

	/**
	 * Devolve o nome do servico
	 **/
	public final String getNome() {
		return "UnEnrollStudentInGroup";
	}

	
	public Integer run(String userName,Integer studentGroupCode) throws FenixServiceException {
	
		try {
			ISuportePersistente persistentSuport = SuportePersistenteOJB.getInstance();

			IPersistentStudentGroup persistentStudentGroup = persistentSuport.getIPersistentStudentGroup();
			IPersistentStudentGroupAttend persistentStudentGroupAttend = persistentSuport.getIPersistentStudentGroupAttend();
			IPersistentStudent persistentStudent = persistentSuport.getIPersistentStudent();
			IFrequentaPersistente persistentAttend = persistentSuport.getIFrequentaPersistente();
			
			IStudentGroup studentGroup = (IStudentGroup) persistentStudentGroup.readByOId(new StudentGroup(studentGroupCode),false);
			IDisciplinaExecucao executionCourse = studentGroup.getGroupProperties().getExecutionCourse();
			IStudent student = (IStudent) persistentStudent.readByUsername(userName);
			IFrequenta attend = (IFrequenta) persistentAttend.readByAlunoAndDisciplinaExecucao(student,executionCourse);
			
			IGroupProperties groupProperties = studentGroup.getGroupProperties();
						
			IStudentGroupAttend studentGroupAttendToDelete = (IStudentGroupAttend) persistentStudentGroupAttend.readBy(studentGroup,attend);
			
			IGroupEnrolmentStrategyFactory enrolmentGroupPolicyStrategyFactory = GroupEnrolmentStrategyFactory.getInstance();
			IGroupEnrolmentStrategy strategy = enrolmentGroupPolicyStrategyFactory.getGroupEnrolmentStrategyInstance(groupProperties);
			
			boolean result = strategy.checkNumberOfGroupElements(groupProperties,studentGroup);	
			
			List allStudentGroupAttend = persistentStudentGroupAttend.readAllByStudentGroup(studentGroup);
					
			if (result && allStudentGroupAttend.contains(studentGroupAttendToDelete))
			{ 
				persistentStudentGroupAttend.delete(studentGroupAttendToDelete);
				return new Integer(1);
							
			} else {
				if(!result && !allStudentGroupAttend.contains(studentGroupAttendToDelete)){
					return new Integer(0);
				} else {
					if(!result)
						return new Integer(2);
					else
						return new Integer(3);
				}
			}		
			
		    } catch (ExcepcaoPersistencia e) {
				throw new FenixServiceException(e);
			  }
	}


}
