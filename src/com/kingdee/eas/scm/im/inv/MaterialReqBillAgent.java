package com.kingdee.eas.scm.im.inv;

import java.util.Enumeration;
import java.io.IOException;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.framework.agent.AgentManager;
import com.kingdee.bos.framework.agent.AgentState;
import com.kingdee.bos.framework.agent.IObjectValueAgent;
import com.kingdee.bos.framework.agent.AgentContainerFactory;
import com.kingdee.bos.framework.agent.AgentUtility;
import com.kingdee.bos.util.BOSObjectType;

public class MaterialReqBillAgent extends MaterialReqBillInfo implements IObjectValueAgent{
    public static final BOSObjectType bosType = new BOSObjectType("500AB75E");
    private final AgentManager manager;

    public MaterialReqBillAgent ()
    {
        manager = new AgentManager();
    }
    
    public static MaterialReqBillAgent copyOvToAgent(IObjectValue vo){
        MaterialReqBillAgent agent = new MaterialReqBillAgent();
        return (MaterialReqBillAgent)agent.manager.copyOvToAgent(agent,vo);
    }

    public static MaterialReqBillAgent copyOvAsNewAgent(IObjectValue vo){
        MaterialReqBillAgent agent = new MaterialReqBillAgent();
        return (MaterialReqBillAgent)agent.manager.copyOvToAgentAsNew(agent,vo);
    }

    public static MaterialReqBillAgent find(IObjectPK id) throws BOSException
    {
		MaterialReqBillAgent agent = new MaterialReqBillAgent();
        return (MaterialReqBillAgent)agent.manager.find(agent, id);
    }
    
    public static MaterialReqBillAgent create() throws BOSException
    {
        return (MaterialReqBillAgent)AgentManager.create(new MaterialReqBillAgent());
    }

    public void agentRemove() throws BOSException
    {
        AgentManager.remove(this);
    }

    public IObjectPK agentSave() throws BOSException
    {
        return AgentManager.save(this);
    }

    public AgentState getAgentState()
    {
        return manager.getAgentState();
    }

    public void setAgentState(AgentState state)
    {
        manager.setAgentState(state);
    }

    public void recursiveSetAgentState(AgentState state) {
    	recursiveSetAgentState(state, new java.util.HashSet());
    }

    public void recursiveSetAgentState(AgentState state, java.util.Set handledSet) {
    	if (handledSet == null) {
    		throw new IllegalArgumentException("The second argument should not be null.");
    	}
    	setAgentState(state);
    	handledSet.add(new Integer(System.identityHashCode(this)));
    	
    	Enumeration enumer = this.keys();
    	Object obj = null;
    	AbstractObjectCollection collection = null;
    	while (enumer.hasMoreElements()) {
    		obj = this.get((String)enumer.nextElement());
    		if (obj instanceof IObjectValueAgent) {
    			if (! handledSet.contains(new Integer(System.identityHashCode(obj)))) {
    				((IObjectValueAgent)obj).recursiveSetAgentState(state, handledSet);
    			}
    		} else if (obj instanceof AbstractObjectCollection) {
    			collection = (AbstractObjectCollection)obj;
    			IObjectValue vo = null;
    			for(int i = 0, j = collection.size(); i < j; i++) {
    				vo = collection.getObject(i);
    				if (vo instanceof IObjectValueAgent) {
    					if (! handledSet.contains(new Integer(System.identityHashCode(vo)))) {    					
    						((IObjectValueAgent)vo).recursiveSetAgentState(state, handledSet);
    					}
    				}
    			}
    		}
    	}
    }


    public IObjectValue getTarget()
    {
        return this;
    }
    
    public IObjectValue getInfoInstance() 
    {
        return manager.getSourceInfo();
    }
    
    public void marshal(com.kingdee.util.marshal.Marshaller marshaller) throws IOException {
		super.marshal(marshaller);		
		manager.marshal(marshaller);		
	}

	public void unmarshal(com.kingdee.util.marshal.Unmarshaller unmarshaller) throws IOException, ClassNotFoundException {
		super.unmarshal(unmarshaller);
		manager.unmarshal(unmarshaller);
	}

}
