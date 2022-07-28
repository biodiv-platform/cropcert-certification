/**
 * 
 */
package cropcert.certification.controller;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

/**
 * 
 * @author vilay
 *
 */
public class ControllerModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(InspectionController.class).in(Scopes.SINGLETON);
		bind(SynchronizationController.class).in(Scopes.SINGLETON);
	}
}
