package ServidorPersistente;

import java.util.Date;
import java.util.List;

import Dominio.IExecutionYear;

/**
 * Created on 11/Fev/2003
 * 
 * @author Jo�o Mota Package ServidorPersistente
 *  
 */
public interface IPersistentExecutionYear extends IPersistentObject {
    /**
     * 
     * @param year
     * @return IExecutionYear
     */
    public IExecutionYear readExecutionYearByName(String year) throws ExcepcaoPersistencia;

    /**
     * 
     * @return ArrayList
     */
    public List readAllExecutionYear() throws ExcepcaoPersistencia;

    /**
     * 
     * @return ArrayList
     */
    public List readNotClosedExecutionYears() throws ExcepcaoPersistencia;

    /**
     * 
     * @param executionYear
     * @return boolean
     */
    public boolean delete(IExecutionYear executionYear);

    public IExecutionYear readCurrentExecutionYear() throws ExcepcaoPersistencia;

    public List readOpenExecutionYears() throws ExcepcaoPersistencia;
    
    public List readExecutionYearsInPeriod(Date start, Date end) throws ExcepcaoPersistencia;
}