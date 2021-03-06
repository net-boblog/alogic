package com.alogic.together.service;

import org.apache.commons.lang3.StringUtils;

import com.alogic.together.LogicletContext;
import com.alogic.together.Script;
import com.anysoft.util.Properties;
import com.anysoft.util.PropertiesConstants;
import com.logicbus.backend.AbstractServant;
import com.logicbus.backend.Context;
import com.logicbus.backend.message.JsonMessage;
import com.logicbus.models.servant.ServiceDescription;

/**
 * TogetherServant
 * 
 * @author duanyy
 *
 */
public class TogetherServant extends AbstractServant {
	protected Script script = null;
	
	@Override
	protected void onDestroy() {
		
	}

	@Override
	protected void onCreate(ServiceDescription sd) {
		Properties p = sd.getProperties();
		
		String bootstrap = PropertiesConstants.getString(p,"bootstrap","",true);
		if (StringUtils.isEmpty(bootstrap)){
			String config = PropertiesConstants.getString(p,"script","");
			if (StringUtils.isNotEmpty(config)){
				script = Script.create(config, p);
			}
		}else{
			String config = PropertiesConstants.getString(p,"script","");
			if (StringUtils.isNotEmpty(config)){
				script = Script.create(bootstrap, config, p);
			}
		}
	}

	@Override
	protected int onJson(Context ctx) throws Exception {
		if (script != null){
			JsonMessage msg = (JsonMessage) ctx.asMessage(JsonMessage.class);
			
			LogicletContext logicletContext = new LogicletContext(null,ctx);
			
			script.execute(msg.getRoot(),msg.getRoot(), logicletContext, null);
		}else{
			ctx.asMessage(JsonMessage.class);
		}
		return 0;
	}

}
