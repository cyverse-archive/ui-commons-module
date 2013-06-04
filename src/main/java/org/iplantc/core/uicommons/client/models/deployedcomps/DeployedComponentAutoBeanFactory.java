/**
 * 
 */
package org.iplantc.core.uicommons.client.models.deployedcomps;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

/**
 * @author sriram
 *
 */
public interface DeployedComponentAutoBeanFactory extends AutoBeanFactory {

    AutoBean<DeployedComponent> getDeployedComponent();

    AutoBean<DeployedComponentList> getDeployedComponentList();
}
