/*
 * Created on Jun 18, 2004
 *
 */
package DataBeans.grant.owner;

import DataBeans.InfoPersonWithInfoCountry;
import Dominio.grant.owner.GrantOwner;
import Dominio.grant.owner.IGrantOwner;


/**
 * @author Pica
 * @author Barbosa
 */
public class InfoGrantOwnerWithPerson extends InfoGrantOwner
{
	public void copyFromDomain(IGrantOwner grantOwner)
	{
		super.copyFromDomain(grantOwner);
		if(grantOwner != null){
			setPersonInfo(InfoPersonWithInfoCountry.newInfoFromDomain(grantOwner.getPerson()));
		}
	}
	
	public static InfoGrantOwner newInfoFromDomain(IGrantOwner grantOwner)
	{
		InfoGrantOwnerWithPerson infoGrantOwner = null;
		if(grantOwner != null){
			infoGrantOwner = new InfoGrantOwnerWithPerson();
			infoGrantOwner.copyFromDomain(grantOwner);
		}
		return infoGrantOwner;
	}
	
	public void copyToDomain(InfoGrantOwner infoGrantOwner,IGrantOwner grantOwner)
    {
        super.copyToDomain(infoGrantOwner,grantOwner);
        grantOwner.setPerson(InfoPersonWithInfoCountry.newDomainFromInfo(infoGrantOwner.getPersonInfo()));
    }
    
    public static IGrantOwner newDomainFromInfo(InfoGrantOwner infoGrantOwner)
    {
        IGrantOwner grantOwner = null;
        InfoGrantOwnerWithPerson infoGrantOwnerWithPerson = null;
        if(infoGrantOwner != null)
        {
            grantOwner = new GrantOwner();
            infoGrantOwnerWithPerson = new InfoGrantOwnerWithPerson();
            infoGrantOwnerWithPerson.copyToDomain(infoGrantOwner,grantOwner);       
        }
        return grantOwner;
    }
	
}
