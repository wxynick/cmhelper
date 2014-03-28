/**
 * 
 */
package com.wxxr.mobile.callhelper;

/**
 * @author neillin
 *
 */
public interface ClosableIteratable<T> extends Iterable<T> {
	void close();
}
